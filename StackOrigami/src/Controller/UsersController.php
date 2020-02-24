<?php

namespace App\Controller;

/* Appel des éléments qui seront utilisés par ce controller */

use App\Entity\Users;
use App\Form\UsersType;
use App\Repository\UsersRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/users")
 */
class UsersController extends AbstractController {

    /**
     * @Route("/", name="users_index", methods={"GET"})
     */
    /* Fonction de recherche des utilisateurs */
    public function index(UsersRepository $usersRepository): Response {
        return $this->render('users/index.html.twig', [
                    'users' => $usersRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="users_new", methods={"GET","POST"})
     */
    /* fonction d'affichage des utilisateurs */
    public function new(Request $request): Response {
        $user = new Users();
        $form = $this->createForm(UsersType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();

            $this->addFlash('add_user', 'Utilisateur ajouté');


            return $this->redirectToRoute('users_index');
        }

        return $this->render('users/new.html.twig', [
                    'user' => $user,
                    'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="users_show", methods={"GET"})
     */
    /* Fonction d'affichage de l'utilisateur */
    public function show(Users $user): Response {
        return $this->render('users/show.html.twig', [
                    'user' => $user,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="users_edit", methods={"GET","POST"})
     */
    /* Fonction d'édition et de verification  de l'utilisateur */
    public function edit(Request $request, Users $user): Response {
        $form = $this->createForm(UsersType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('profil');
        }

        return $this->render('users/edit.html.twig', [
                    'user' => $user,
                    'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="users_delete", methods={"DELETE"})
     */
    /* Fonction de suppression, vérification d'identité et de droits pour les utilisateurs */
    public function delete(Request $request, Users $user): Response {
        if ($this->isCsrfTokenValid('delete' . $user->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($user);
            $entityManager->flush();
        }

        return $this->redirectToRoute('users_index');
    }

}
