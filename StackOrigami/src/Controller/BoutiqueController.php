<?php

namespace App\Controller;

/* Appel des éléments qui seront utilisés par ce controller */
use App\Entity\PropertySearch;
use App\Form\PropertySearchType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\ContactType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use App\Repository\ProductRepository;
use App\Repository\ProductCategoryRepository;
use App\Repository\PartnerRepository;

class BoutiqueController extends AbstractController {

    public function __construct(EntityManagerInterface $em) {
        $this->em = $em;
    }

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
                    /* Retrouve toutes les valeurs dans l'entité partners */
                    'partners' => $partnerRepository->findAll()
        ]);
    }

    /**
     * @Route("/contact", name="contact")
     */
    /* Fonction qui créer un formulaire et vérifie la requete */
    public function contact(Request $request) {
        /* Création du formulaire ContactType */
        $form = $this->createForm(ContactType::class);
        /* Execution de la requete */
        $form->handleRequest($request);
        /* renvoie la fonction de création de formulaire sur la vue */
        return $this->render('boutique/contact.html.twig', [
                    /* Envoie sous le nom 'form' la fonction createView */
                    'form' => $form->createView()
        ]);
    }

    /**
     * @Route("/about", name="about_us")
     */
    public function about() {
        /* Renvoie vers la page about_us */
        return $this->render('boutique/about_us.html.twig');
    }

    /**
     * @Route("/catalog", name="catalog")
     */
    public function catalog(ProductRepository $productRepository, Request $request,PaginatorInterface $paginator) {
        /* Impute la nouvelle recherche a la variable */
        $search = new PropertySearch();
        /* On donne au formulaire la recherche */
        $form = $this->createForm(PropertySearchType::class, $search);
        /* Execution de la requete */
        $form->handleRequest($request);
        $products = $paginator->paginate(
            $this->repository->findAllVisible($search),
            $request->query->getInt('page',1),
            24
        );
        /* renvoie la fonction de recherche et de création sur la vue */
        return $this->render('product/index.html.twig', [
                    /* Envoie sous le nom 'products' la fonction findAllVisible de la variable search */
                    'products' => $productRepository->findAllVisible($search),
                    /* Envoie sous le nom 'form' la fonction createView */
                    'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/profil", name="profil")
     */
    public function profil() {
        /* Renvoie vers la page profil */
        return $this->render('boutique/profil.html.twig');
    }

}
