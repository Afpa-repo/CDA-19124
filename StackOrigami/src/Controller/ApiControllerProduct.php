<?php


namespace App\Controller;


use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use App\Repository\ProductRepository;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class ApiControllerProduct extends AbstractController
{
    /**
     * @param ProductRepository $productRepository
     * @return JsonResponse
     * @Route("/api/listProduct", name="api_get_product", methods={"GET"})
     */
    public function list(ProductRepository $productRepository)
    {
        return $this->json($productRepository->findAll(), 200, [], ['groups' => 'Api:Product']);
    }

}