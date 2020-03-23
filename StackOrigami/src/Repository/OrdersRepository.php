<?php

namespace App\Repository;

use App\Entity\Orders;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Common\Persistence\ManagerRegistry;
use Doctrine\ORM\QueryBuilder;
use App\Repository\UsersRepository;

/**
 * @method Orders|null find($id, $lockMode = null, $lockVersion = null)
 * @method Orders|null findOneBy(array $criteria, array $orderBy = null)
 * @method Orders[]    findAll()
 * @method Orders[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class OrdersRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Orders::class);
    }

    /**
     * @return QueryBuilder
     */
    public function allbills(): QueryBuilder {
        return $query = $this->createQueryBuilder('b')
                ->select('SUM(b.Total) AS Total, u.id')
                // ->from('orders')
                ->join('b.UsersID','u')
                //->where('o.users_id_id = b.id')
                ->groupBy('u.id')
                ->orderBy('Total','DESC');
            }   

    /**
     *
     */
    public function usersTotal()
    {
        return $this->allbills()->getQuery()
            ->getResult();
    }

    /**
     * @param $id
     * @return mixed
     */
    public function totalID($id)    //total des commandes pour un utilisateur
    {
        $total = $this->createQueryBuilder('b')           
            ->select('SUM(b.Total) AS Total')
                // ->from('orders')
            ->join('b.UsersID','u')  
            
            ->where('u.id = :id')
            ->setParameter(':id', $id)
            ->groupBy('u.id')
            ->orderBy('Total','DESC');

        if( $total->getQuery()->getResult()==[]){
            return [["Total" => "0"]];
        }
        return $total->getQuery()
            ->getResult();
    }

    // /**
    //  * @return Orders[] Returns an array of Orders objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('o.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Orders
    {
        return $this->createQueryBuilder('o')
            ->andWhere('o.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
