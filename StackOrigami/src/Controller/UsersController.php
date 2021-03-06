<?php

namespace App\Controller;

/* Appel des éléments qui seront utilisés par ce controller */

use App\Entity\Users;
use App\Form\UsersType;
use App\Form\UsersNewType;
use App\Form\UsersAdminType;
use App\Repository\UsersRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
//Pour la modification du mot de passe
use App\Entity\Password;
use App\Form\PasswordsType;
use Symfony\Component\Form\FormError;   //pour ajouter des erreurs à afficher
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
//Pour l'historique des commandes
use App\Entity\Orders;
use App\Repository\OrdersRepository;
use App\Entity\OrderDetails;
use App\Repository\OrderDetailsRepository;

/**
 * @Route("/users")
 */
class UsersController extends AbstractController {

    /**
     * @Route("/", name="users_index", methods={"GET"})
     */
    /* Fonction de recherche des utilisateurs */
    public function index(UsersRepository $usersRepository, OrdersRepository $ordersRepository): Response {
        //dd($ordersRepository->usersTotal());
        //dd($ordersRepository->TotalID(3));
        //$tab=new Array();
        /*
        $i=0;
        foreach($usersRepository->findAll() as $user ){
            $tab[
            $i++;
        }
        $tab[0]=$usersRepository->findAll();
        $tab[1]=*/
        //dd($ordersRepository->usersTotal());
        //dd($usersRepository->findByRole(1));
        return $this->render('users/index.html.twig', [
                    'users' => $usersRepository->findAll(),
                    /*'allbills' => $ordersRepository->usersTotal(),
                    */
        ]);
    }

    /**
     * @Route("/new", name="users_new", methods={"GET","POST"})
     */
    /* fonction d'affichage des utilisateurs */
    public function new(Request $request, UserPasswordEncoderInterface $encoder, UsersRepository $usersRepository): Response {
        $user = new Users();
        $form = $this->createForm(UsersNewType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /* Impute l'encodage qui sera utilisé pour crypter le mot de passe utilisateur dans la bdd */
            $hash = $encoder->encodePassword($user, $user->getPassword());
            /* Cryptage du mot de passe que l'utilisateur aura entré */
            $user->setPassword($hash);
            if($user->getRole()==''){   //si l'utilisateur n'a pas de role
                $user->setRole(0);  //on le met en client
            }
            if($user->getType()){   //si le client est un particulier
                $user->setCoefficient(1);   //met le coefficient initial
            }else{  //si c'est une entreprise
                $user->setCoefficient(2);
            }
            /* Associe l'utilisateur à un commercial*/
            if($user->getType()){   //si le client est un particulier
                $user->setCoefficient(1);   //met le coefficient initial
                if($usersRepository->findByRole(2) != []){
                    $commercial = $usersRepository->findByRole(2)[0];   //récupèle le premier commercial 
                    $user->setCommercial($commercial);  //ajoute le commercial à l'utilisateur
                }
            }else{  //si c'est une entreprise
                $user->setCoefficient(2);
                if($usersRepository->findByRole(3) != []){
                    $commercial = $usersRepository->findByRole(3)[0];   //récupèle le premier commercial 
                    $user->setCommercial($commercial);  //ajoute le commercial à l'utilisateur
                }
            }
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($user);
            $entityManager->flush();

            /*$this->addFlash('add_user', 'Utilisateur ajouté');*/


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
    public function show(Users $user,OrdersRepository $ordersRepository): Response {
        $total=$ordersRepository->TotalID($user->getID());
        //dd($total[0]);
        return $this->render('users/show.html.twig', [
                    'user' => $user,
                    'total' => $total[0]
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
            if($user->getType()){   //si le client est un particulier
                $user->setCoefficient(1);   //met le coefficient initial
            }else{  //si c'est une entreprise
                $user->setCoefficient(2);
            }
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('profil');
        }

        return $this->render('users/edit.html.twig', [
                    'user' => $user,
                    'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/{id}/editAdmin", name="users_editAdmin", methods={"GET","POST"})
     */
    /* Fonction d'édition et de verification  de l'utilisateur */
    public function editAdmin(Request $request, Users $user, UsersRepository $usersRepository): Response {
        $form = $this->createForm(UsersAdminType::class, $user);
        $form->handleRequest($request);
        //dd($usersRepository->findByRole(1));


        if ($form->isSubmitted() && $form->isValid()) {
            if($user->getRole()==''){   //si l'utilisateur n'a pas de role
                $user->setRole(0);  //on le met en client
            }
            /* associe à un commercial */
            $commercial = new Users();
            if($user->getType()){   //si le client est un particulier
                $user->setCoefficient(1);   //met le coefficient initial
                if($usersRepository->findByRole(2) != []){
                    $commercial = $usersRepository->findByRole(2)[0];   //récupèle le premier commercial 
                    $user->setCommercial($commercial);  //ajoute le commercial à l'utilisateur
                }
                //$user->setCommercial($usersRepository)
            }else{  //si c'est une entreprise
                $user->setCoefficient(2);   //met le coeficient initial
                if($usersRepository->findByRole(3) != []){
                    $commercial = $usersRepository->findByRole(3)[0];   //récupèle le premier commercial 
                    $user->setCommercial($commercial);  //ajoute le commercial à l'utilisateur
                }
            }
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('users_index');
        }

        return $this->render('users/edit_admin.html.twig', [
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
            $this->get('security.token_storage')->setToken(null);   //force la déconnexion
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($user);  //crée la requête de suppression
            $entityManager->flush();    //lance la requête
        }
        return $this->redirectToRoute('home');
    }
    
    /**
     * @Route("/{id}/password", name="users_ChangePassword", methods={"GET","POST"})
     */
    public function ChangePassword(Request $request, Users $user,UserPasswordEncoderInterface $encoder): Response
    {
        $password = new Password(); //Crée un nouvel objet de type password
        $form = $this->createForm(PasswordsType::class, $password); //crée un formulaire pour cet objet
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $data=$form->getData();
            $oldPassword = $data->getOldPassword(); //récupère la l'ancien mot de passe entrée par l'utilisateur
            $newPassword = $data->getNewPassword(); //récupère le nouveau mot de passe
            if(password_verify($oldPassword,$user->getPassword())){  //Si l'ancien mot de passe correspond
                $entityManager = $this->getDoctrine()->getManager();
                $user->setPassword(password_hash($newPassword,PASSWORD_BCRYPT));   //hash le nouveau mot de passe 
                $entityManager->persist($user);
                $entityManager->flush();
                return $this->redirectToRoute('profil');    //redirige sur la page profil
            }else{   //sinon, l'ancien mot de passe ne correspond pas 
                $form->get('oldPassword')->addError(new FormError('Ancien mot de passe incorrect'));    //On ajoute l'erreur à afficher
            }
            
        }

        return $this->render('users/edit_password.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}/orders", name="users_orders", methods={"GET"})
     */
    /* Fonction d'affichage de l'historique de commande de l'utilisateur */
    public function orders(Users $user, OrdersRepository $ordersRepository, OrderDetailsRepository $orderDetailsRepository, UsersRepository $usersRepository) {
        /* récupère les commandes de l'utilisateur */
        $orders = $ordersRepository->findBy(array('UsersID' => $user->getId()));
        /* Renvoie vers la page profil */
        return $this->render('users/users_orders.html.twig', [
                    'user' => $user,
                    'orders' => $orders,
                    'allbills' => $ordersRepository->usersTotal(),
        ]);
    }
}