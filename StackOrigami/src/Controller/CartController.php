<?php

namespace App\Controller;

/* Appel des éléments qui seront utilisés par ce controller */

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\Session\Session;
use App\Entity\Product;
use App\Repository\ProductRepository;

class CartController extends AbstractController {

    /**
     * @Route("/cart", name="cart_index")
     */
    public function index(SessionInterface $session, ProductRepository $productRepository) {
        $count = 0;
        /* Impute a la variable '$panier' la valeur récupérée 'panier' */
        $panier = $session->get('panier', []);
        /* Indique que la variable $panierWithData est un tableau */
        $panierWithData = [];
        /* Pour chaque variable id/quantité dans la variable 'panier' */
        foreach ($panier as $id => $quantity) {
            /* On impute un autre tableau dans le tableau de variable 'panierWithData' les valeurs 'product' et 'quantity' */
            $panierWithData[] = [
                'product' => $productRepository->find($id),
                'quantity' => $quantity
            ];
        }
        /* On impute la valeur 0 à la variable total */
        $total = 0;
        /* Pour chaque variable 'item' dans le tableau de valeur 'panierWithData' */
        foreach ($panierWithData as $item) {
            /* Impute a la variable $totalItem la multiplication de l'object 'product' à l'object 'quantity' */
            $totalItem = $item['product']->getPrice() * $item['quantity'];
            /* Additionne la variable 'totalItem' a la variable 'total' */
            $total += $totalItem;
            /* Additionne la valeur 'quantity' de la variable 'item' à la variable 'count' */
            $count += $item['quantity'];
        }
        //ajoute un champs Fcount a la session avec le nombre de produit total dans le panier 
        $session->set('Fcount', $count);
        /* Renvoie la fonction sur la vue */
        return $this->render('cart/index.html.twig', [
                    'items' => $panierWithData,
                    'total' => $total,
                    'Cquantity' => $count
        ]);
    }

    /**
     * @Route("/cart/add/{id}" , name="cart_add")
     */
    public function add($id, SessionInterface $session) {
        /* Récupère la valeur 'panier' et l'impute a la variable 'panier', dans le cas où il ne récupère rien, il créer un tableau vide */
        $panier = $session->get('panier', []);
        /* Récupère la valeur 'Fcount' et l'impute à la variable 'count', dans le cas où il récupère rien, il impute la valeur 0 */
        $count = $session->get('Fcount', 0);
        /* Si la variable 'id' de la variable 'panier' n'est pas vide */
        if (!empty($panier[$id])) {
            /* Ajoute 1 à la variable 'id' du tableau 'panier' */
            $panier[$id]++;
            /* Ajoute 1 à la variable 'count' */
            $count++;
        } else {
            /* Impute la valeur 1 à la variable 'id' du tableau 'panier' */
            $panier[$id] = 1;
            /* Ajoute 1 à la variable 'count' */
            $count++;
        }
        /* Attribution de la valeur 'panier' à la variable 'panier' */
        $session->set('panier', $panier);
        /* Attribution de la valeur 'Fcount' à la variable 'count' */
        $session->set('Fcount', $count);
        /* Flash affiche une notification sur la vue catalog */
        $this->addFlash('add_product', 'Produit ajouté au panier');

        /* Retourne l'évènement en cours vers 'catalog' */
        return $this->redirectToRoute("catalog");
    }

    /**
     * @Route("/cart/remove/{id}", name="cart_remove")
     */
    public function remove($id, SessionInterface $session) {
        /* Récupère la valeur 'panier' et l'impute à la variable 'panier, dans le cas où il ne récupère rien, il créer un tableau vide */
        $panier = $session->get('panier', []);
        /* Si la variable 'id' de la variable 'panier' n'est pas vide */
        if (!empty($panier[$id])) {
            /* Vidage du panier */
            unset($panier[$id]);
        }
        /* Attribution de la valeur 'panier' à la variable 'panier' */
        $session->set('panier', $panier);

        /* Flash affiche une notification sur la vue cart */
        $this->addFlash('delete_product', 'Produit retiré du panier');

        /* Retourne l'évèneent en cours vers la page 'cart_index' */
        return $this->redirectToRoute("cart_index");
    }

}
