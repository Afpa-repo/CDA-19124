<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\Session\Session;
use App\Entity\Product;
use App\Repository\ProductRepository;

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
        // code...
      }

      $total = 0;
      foreach ($panierWithData as $item) {
          
          $totalItem = $item['product']->getPrice()*$item['quantity'];
          $total += $totalItem;// code...
          $count += $item['quantity']; 
      }
      //ajoute un champs Fcount a la session avec le nombre de produit total dans le panier 
      $session->set('Fcount',$count);
      
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
      $count = $session->get('Fcount',[]);
      if(!empty($panier[$id])){
        $panier[$id]++;
        $count ++;
      }else{
        $panier[$id] =1;
        $count=1;
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
        unset($panier[$id]);
      }
      $session->set('panier',$panier);

        // Flash affiche une notification sur la vue cart
        $this->addFlash('delete_product', 'Produit retiré du panier');


        return $this->redirectToRoute("cart_index");
    }
}
