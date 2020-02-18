<?php
//Entity non relié a la base de données, utiliser pour les filtres dans le catalogue

namespace App\Entity;

class PropertySearch{

    /**
     * @var int|null
     */
    private $maxPrice;

    /**
     * @return int|null
     */
    public function getMaxPrice(): ?int
    {
        return $this->maxPrice;
    }

    /**
     * @param int|null $maxPrice
     * @return PropertySearch
     */
    public function setMaxPrice(int $maxPrice): PropertySearch
    {
        $this->maxPrice = $maxPrice;
        return $this;
    }
}