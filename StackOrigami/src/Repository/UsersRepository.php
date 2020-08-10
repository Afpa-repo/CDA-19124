<?php

namespace App\Repository;

use App\Entity\Users;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Common\Persistence\ManagerRegistry;
use Doctrine\ORM\QueryBuilder;

/**
 * @method Users|null find($id, $lockMode = null, $lockVersion = null)
 * @method Users|null findOneBy(array $criteria, array $orderBy = null)
 * @method Users[]    findAll()
 * @method Users[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class UsersRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Users::class);
    }

     /**
     * @return QueryBuilder
     */
    public function allbills(): QueryBuilder {
        return $query = $this->createQueryBuilder('b')
                ->select('SUM(b.total) AS Total, o.user_id_id AS userID')
                // ->from('orders')
                ->join('b.Orders','o')
                //->where('o.users_id_id = b.id')
                ->groupBy('o.users_id_id')
                ->orderBy('Total','DESC');
            }   

    /**
     *
     */
    public function users_total()
    {
        return $this->allbills()->getQuery()
            ->getResult();
    }

    /**
     * Retourne les users ayant le role demandé
     */
    public function findByRole($value)
    {
        return $this->createQueryBuilder('u')
            ->andWhere('u.role = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getResult()
        ;
    }


    public function findByMail($value)
        {
            return $this->createQueryBuilder('u')
                ->andWhere('u.mail = :val')
                ->setParameter('val', $value)
                ->getQuery()
                ->getResult()
            ;
        }

    // /**
    //  * @return Users[] Returns an array of Users objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('u')
            ->andWhere('u.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('u.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Users
    {
        return $this->createQueryBuilder('u')
            ->andWhere('u.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
