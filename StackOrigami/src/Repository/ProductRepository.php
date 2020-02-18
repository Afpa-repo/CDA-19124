<?php

namespace App\Repository;

use App\Entity\Product;
use App\Entity\PropertySearch;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Common\Persistence\ManagerRegistry;
use Doctrine\ORM\Query;
use Doctrine\ORM\QueryBuilder;
use Symfony\Component\HttpFoundation\Request;

/**
 * @method Product|null find($id, $lockMode = null, $lockVersion = null)
 * @method Product|null findOneBy(array $criteria, array $orderBy = null)
 * @method Product[]    findAll()
 * @method Product[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProductRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Product::class);
    }

    public function findAllVisible(PropertySearch $search) //function qui affichera les produits dans le catalogue
    {
        $query = $this->findVisibleQuery();

        if ($search->getMaxPrice()) {
            $query = $query->andWhere('p.price <= :maxprice');
            $query->setParameter('maxprice', $search->getMaxPrice());
        }
        if ($search->getSelectedCategory()) {
            $query = $query->andWhere('p.productCategory = :selectedcategory');
            $query->setParameter('selectedcategory', $search->getSelectedCategory());
        }
        if($search->getSearchbar()){
            $query = $query->andwhere('p.libelle = :searchbar');
            $query->setParameter('searchbar', $search->getSearchbar());
        }

        if($search->getOrderBy1() && $search->getOrderBy1() == 1) { //1 = prix croissant 2 = prix decroissant
            $query->orderBy( 'p.price', 'ASC');
        }
        if($search->getOrderBy1() && $search->getOrderBy1() == 2) {
            $query->orderBy( 'p.price', 'DESC');
        }
        if($search->getOrderBy1() && $search->getOrderBy1() == 3) {
            $query->orderBy( 'p.createdAt', 'ASC');
        }
        if($search->getOrderBy1() && $search->getOrderBy1() == 4) {
            $query->orderBy( 'p.createdAt', 'DESC');
        }

        return $query->getQuery()
            ->getResult();
    }

    private function findVisibleQuery(): QueryBuilder
    {
        return $this->createQueryBuilder('p')
            ->where('p.stock != 0');
    }

    // /**
    //  * @return Product[] Returns an array of Product objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Product
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
