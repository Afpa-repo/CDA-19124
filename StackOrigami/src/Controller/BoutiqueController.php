<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\ContactType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

class BoutiqueController extends AbstractController {

    /**
     * @Route("/", name="home")
     */
    public function home() {
        return $this->render('boutique/home.html.twig');
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

}
