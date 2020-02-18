<?php

namespace App\Controller;

/* Appel  */

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\ContactType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use App\Entity\Product;
use App\Entity\ProductCategory;
use App\Entity\Partner;
use App\Form\ProductType;
use App\Repository\ProductRepository;
use App\Repository\ProductCategoryRepository;
use App\Repository\PartnerRepository;
use App\Form\ProductCategoryType;
use App\Controller\CartController;

class BoutiqueController extends AbstractController {

    /**
     * @Route("/", name="home", methods={"GET"})
     */
    /* Fonction qui appelle dans la page home tous les produits, tous les catégories et tous les partenaires de la BDD */
    public function home(ProductRepository $productRepository, ProductCategoryRepository $productCategoryRepository, PartnerRepository $partnerRepository): Response {
        return $this->render('boutique/home.html.twig', [
                    /* Retrouve toutes les valeurs dans l'entité products */
                    'products' => $productRepository->findAll(),
                    /* Retrouve toutes les valeurs dans l'entité product_categories */
                    'product_categories' => $productCategoryRepository->findAll(),
                    /* Retrouve toutes les valeurs das l'entité partners */
                    'partners' => $partnerRepository->findAll()
        ]);
    }

    /**
     * @Route("/contact", name="contact")
     */
    /* Fonction qui créer un formulairevérifie la requete */
    public function contact(Request $request) {
        /* Création du formulaire ContactType */
        $form = $this->createForm(ContactType::class);
        /* Saisie de la requete */
        $form->handleRequest($request);
                /* renvoie un formulaire */
        return $this->render('boutique/contact.html.twig', [
                    'form' => $form->createView()
        ]);
    }

    /**
     * @Route("/about", name="about_us")
     */
    public function about() {
        return $this->render('boutique/about_us.html.twig');
    }

    /**
     * @Route("/catalog", name="catalog")
     */
    public function catalog(ProductRepository $productRepository) {
        return $this->render('product/index.html.twig', [
                    'products' => $productRepository->findAll(),
        ]);
    }

    /**
     * @Route("/profil", name="profil")
     */
    public function profil() {
        return $this->render('boutique/profil.html.twig');
    }

}
