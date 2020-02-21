<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Repository\ProductRepository;
use App\Entity\Product;
use App\Entity\ProductCategory;
use App\Entity\Orders;
use App\Entity\Users;
use App\Entity\OrderDetails;
use Symfony\Component\Security\Core\User\UserInterface;

class CartController extends AbstractController
{
    /**
     * @Route("/cart", name="cart_index")
     */
    public function index(SessionInterface $session, ProductRepository $productRepository)
    {
      $count =0;
      $panier=$session->get('panier',[]);
      $panierWithData= [];
      foreach ($panier as $id => $quantity) {
        $panierWithData[] = [
          'product' => $productRepository->find($id),
          'quantity' => $quantity
        ];
      }

      $total = 0;
      foreach ($panierWithData as $item) {          
          $totalItem = $item['product']->getPrice()*$item['quantity'];
          $total += $totalItem;
          $count += $item['quantity']; 
      }
      //ajoute un champs Fcount a la variable session avec le nombre de produit total dans le panier 
      $session->set('Fcount',$count);
      $session->set('FullCart',$panierWithData);
      //dd($session);
      // Si Le panier est valider alors vide le panier .
      if($session->get('Validate',"false")=="true"){
            unset($panierWithData);
            $session->remove('Validate');
            $session->remove('panier');
            $session->set('Fcount',0);
            return $this->redirectToRoute("profil");
        }
        return  $this->render('cart/index.html.twig', [
          'items' => $panierWithData,
          'total' => $total,
          'Cquantity' => $count 
        ]) ;
    }
    /**
    * @Route("/cart/add/{id}" , name="cart_add")
    */
    public function add($id, SessionInterface $session)
    {
      $panier = $session->get('panier',[]);
      $count = $session->get('Fcount',0);
      if(!empty($panier[$id])){
        $panier[$id]++;
        $count ++;
      }else{
        $panier[$id] =1;
        $count ++;
      }
      
      $session->set('panier',$panier);
      $session->set('Fcount',$count);
      // Flash affiche une notification sur la vue catalog
        $this->addFlash('add_product', 'Produit ajouté au panier');

       
        return $this->redirectToRoute("catalog");
    }
    /**
    * @Route("/cart/remove/{id}", name="cart_remove")
    */
    public function remove($id, SessionInterface $session){
      
      $panier= $session->get('panier',[]);
      if(!empty($panier[$id])){        
          if($_POST['nb_product']<=0){              
                    unset($panier[$id]);
               }else{
               $panier[$id]= $_POST['nb_product'];          
               }            
      }else{
      unset($panier[$id]);
      }
      $session->set('panier',$panier);

        // Flash affiche une notification sur la vue cart
        $this->addFlash('delete_product', 'Quantité mis à jour');


        return $this->redirectToRoute("cart_index");
    }
    
    /**
    * @Route("/cart/validate", name="cart_validate")
    */
    
    // Validation du panier.
    public function validate(SessionInterface $session, UserInterface $user) {         
        $order = new Orders();  //Crée un objet Orders
        $order->setUsersID($user);    //récupère les valeurs
        $order->setDate(new \DateTime());
        $order->setStatus("En cours de traitement"); // a changer dans l'interface employés
        /*** Rajouter les valeurs ***/
        $order->setBill('jeej');
        $order->setDeliveryForm('jeej');
        
         $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($order);
            $entityManager->flush();
            
            foreach ($session->get('FullCart',[])as $produit){  //pour chaque produit
                $orderDetails = new OrderDetails(); //on crée un objet OrderDetails
                $orderDetails->setOrders($order);//récupere la valeur de order
                $orderDetails->setQuantity($produit['quantity']); 
                $orderDetails->setProduct($produit['product']);
            $entityManager->merge($orderDetails);
            $entityManager->flush();    //On le rajoute dans la base de donnée
            }
            
            $session->set('Validate',"true");
        return $this->redirectToRoute("cart_index");    //redirige vers le profil
    }
    
    
    
      }
