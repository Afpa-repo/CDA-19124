<?php

namespace App\Controller;

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
    public function home(ProductRepository $productRepository, ProductCategoryRepository $productCategoryRepository, PartnerRepository $partnerRepository): Response {
        return $this->render('boutique/home.html.twig', [
                    'products' => $productRepository->findAll(),
                    'product_categories' => $productCategoryRepository->findAll(),
                    'partners' => $partnerRepository->findAll()
        ]);


    }

    /**
     * @Route("/contact", name="contact")
     */
    public function contact(Request $request) {
        $form = $this->createForm(ContactType::class);
        $form->handleRequest($request);
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
