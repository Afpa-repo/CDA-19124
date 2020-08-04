<?php

namespace App\Controller;

use App\Entity\Users;
use App\Repository\UsersRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Exception\NotEncodableValueException;
use Symfony\Component\Serializer\SerializerInterface;

class ApiControllerUsers extends AbstractController
{
    /**
     * @Route("/api/listUser", name="api_get_client", methods={"GET"})
     */
    public function list(UsersRepository $usersRepository)
    {
        return $this->json($usersRepository->findAll() , 200, [], ['groups' => 'Api:Client']);
    }

    /**
     * @Route("/api/addUser", name="api_post_client", methods={"POST"})
     */
    public function add(Request $request, SerializerInterface $serializer, EntityManagerInterface $em)
    {
        $json = $request->getContent();
        try  {
            $user = $serializer->deserialize($json, Users::class, 'json');
            $em->persist($user);
            $em->flush();

            return $this->json($user, 201, [], ['groups' => 'Api:Client']);
        } catch(NotEncodableValueException $e) {
            return $this->json([
                'status' => 400,
                'message' => $e->getMessage()
            ], 400);
        }
    }

    /**
     * @Route("/api/deleteUser/{id}", name="api_delete_client", methods={"DELETE"})
     */
    public function delete(Users $user, UsersRepository $usersRepository)
    {
        try {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($user);
            $entityManager->flush();

            return $this->json($usersRepository->findAll(), 200, [], ['groups' => 'Api:Client']);
        } catch (NotEncodableValueException $e) {
            return $this->json([
                'status' => 400,
                'message' => $e->getMessage()
            ], 400);
        }
    }

    /**
     * @Route("/api/updateUser/{id}", name="api_update_client", methods={"PUT"})
     */
    public function update(?Users $user, UsersRepository $usersRepository, Request $request, SerializerInterface $serializer, EntityManagerInterface $em)
    {
        $json = $request->getContent();
        $json = $serializer->deserialize($json, Users::class, 'json');
        $status = 200;

        if (!$user) {
            $user = new Users();
            $status = 201;
        }

        if (!empty($json->getMail()))
        {
            $user->setMail($json->getMail());
        }
        if (!empty($json->getFirstName()))
        {
            $user->setFirstName($json->getFirstName());
        }
        if (!empty($json->getSurname()))
        {
            $user->setSurname($json->getSurname());
        }
        if (!empty($json->getPhoneNumber()))
        {
            $user->setPhoneNumber($json->getPhoneNumber());
        }
        if (!empty($json->getAddressFact()))
        {
            $user->setAddressFact($json->getAddressFact());
        }
        if (!empty($json->getCoefficient()))
        {
            $user->setCoefficient($json->getCoefficient());
        }
        if (!empty($json->getType()))
        {
            $user->setType($json->getType());
        }
        if (!empty($json->getRole()))
        {
            $user->setRole($json->getRole());
        }
        if (!empty($json->getSiret()))
        {
            $user->setSiret($json->getSiret());
        }
        if (!empty($json->getCommercial()))
        {
            $user->setCommercial($json->getCommercial());
        }

        $em->persist($user);
        $em->flush();

        return $this->json($user, $status, [], ['groups' => 'Api:Client']);
    }
}
