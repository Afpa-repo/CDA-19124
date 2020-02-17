<?php

namespace App\Controller;

use App\Entity\Newletters;
use App\Form\NewlettersType;
use App\Repository\NewlettersRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/newletters")
 */
class NewlettersController extends AbstractController
{
    /**
     * @Route("/", name="newletters_index", methods={"GET"})
     */
    public function index(NewlettersRepository $newlettersRepository): Response
    {
        return $this->render('newletters/index.html.twig', [
            'newletters' => $newlettersRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="newletters_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $newletter = new Newletters();
        $form = $this->createForm(NewlettersType::class, $newletter);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $newletter->setDate(new \DateTime());
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($newletter);
            $entityManager->flush();

            return $this->redirectToRoute('newletters_index');
        }

        return $this->render('newletters/new.html.twig', [
            'newletter' => $newletter,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="newletters_show", methods={"GET"})
     */
    public function show(Newletters $newletter): Response
    {
        return $this->render('newletters/show.html.twig', [
            'newletter' => $newletter,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="newletters_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Newletters $newletter): Response
    {
        $form = $this->createForm(NewlettersType::class, $newletter);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('newletters_index');
        }

        return $this->render('newletters/edit.html.twig', [
            'newletter' => $newletter,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="newletters_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Newletters $newletter): Response
    {
        if ($this->isCsrfTokenValid('delete'.$newletter->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($newletter);
            $entityManager->flush();
        }

        return $this->redirectToRoute('newletters_index');
    }
}
