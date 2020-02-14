<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass="App\Repository\ProductCategoryRepository")
 */
class ProductCategory
{
    /**
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $name;

    /**
     * @ORM\ManyToMany(targetEntity="App\Entity\productcategory")
     */
    private $sub_category;

    public function __construct()
    {
        $this->sub_category = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    /**
     * @return Collection|productcategory[]
     */
    public function getSubCategory(): Collection
    {
        return $this->sub_category;
    }

    public function addSubCategory(productcategory $subCategory): self
    {
        if (!$this->sub_category->contains($subCategory)) {
            $this->sub_category[] = $subCategory;
        }

        return $this;
    }

    public function removeSubCategory(productcategory $subCategory): self
    {
        if ($this->sub_category->contains($subCategory)) {
            $this->sub_category->removeElement($subCategory);
        }

        return $this;
    }
}