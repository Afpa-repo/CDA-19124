<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass="App\Repository\ProductRepository")
 * @ApiResource
 */
class Product
{

    /**
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     * @Groups("Api:Product")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("Api:Product")
     */
    private $libelle;

    /**
     * @ORM\Column(type="text")
     * @Groups("Api:Product")
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("Api:Product")
     */
    private $color;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("Api:Product")
     */
    private $picture;

    /**
     * @ORM\Column(type="float")
     * @Groups("Api:Product")
     */
    private $price;

    /**
     * @ORM\Column(type="integer")
     * @Groups("Api:Product")
     */
    private $stock;

    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\ProductCategory", inversedBy="products")
     * @ORM\JoinColumn(nullable=false)
     * @Groups("Api:Product")
     */
    private $productCategory;

    /**
     * @ORM\Column(type="integer", length=10, nullable=true)
     * @Groups("Api:Product")
     */
    private $stars;

    /**
     * @ORM\Column(type="datetime")
     * @Groups("Api:Product")
     */
    private $createdAt;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLibelle(): ?string
    {
        return $this->libelle;
    }

    public function setLibelle(string $libelle): self
    {
        $this->libelle = $libelle;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getColor(): ?string
    {
        return $this->color;
    }

    public function setColor(string $color): self
    {
        $this->color = $color;

        return $this;
    }

    public function getPicture(): ?string
    {
        return $this->picture;
    }

    public function setPicture(string $picture): self
    {
        $this->picture = $picture;

        return $this;
    }

    public function getPrice(): ?float
    {
        return $this->price;
    }

    public function setPrice(float $price): self
    {
        $this->price = $price;

        return $this;
    }

    public function getStock(): ?int
    {
        return $this->stock;
    }

    public function setStock(int $stock): self
    {
        $this->stock = $stock;

        return $this;
    }


    public function getProductCategory(): ?ProductCategory
    {
        return $this->productCategory;
    }

    public function setProductCategory(?ProductCategory $productCategory): self
    {
        $this->productCategory = $productCategory;
        return $this;
    }

    public function getStars(): ?int
    {
        return $this->stars;
    }

    public function setStars(?string $stars): self
    {
        $this->stars = $stars;


        return $this;
    }

    public function getCreatedAt(): ?\DateTimeInterface
    {
        return $this->createdAt;
    }

    public function setCreatedAt(\DateTimeInterface $createdAt): self
    {
        $this->createdAt = $createdAt;

        return $this;
    }


}
