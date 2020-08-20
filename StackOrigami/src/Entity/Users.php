<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Validator\Constraints as Assert;
use ApiPlatform\Core\Annotation\ApiResource;

/**
 * @ORM\Entity(repositoryClass="App\Repository\UsersRepository")
 * @ApiResource
 * @UniqueEntity(
 *     fields={"mail"},
 *     message= "L'email que vous avez rentré est déjà utilisé"
 * )
 */
class Users implements UserInterface
{
    /**
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     * @Groups("Api:Client")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(
     *      message="Veuillez rentrer un E-mail"
     * )
     * @Assert\Email(
     *      message="Veuillez rentrer un e-mail valide"
     * )
     * @Groups("Api:Client")
     */
    private $mail;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(
     *      message="Veuillez rentrer un mot de passe"
     * )
     * @Assert\Length(
     *      min="8",
     *      minMessage="Veuillez rentrer au moins 8 caractères",
     *      max=255,
     *      maxMessage="Veuillez rentrer moins de 255 caractères"
     * )
	 * @Groups("Api:Client")
     */
    private $password;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(
     *      message="Veuillez rentrer un nom"
     * )
     * @Assert\Length(
     *      max=255,
     *      maxMessage="Veuillez rentrer moins de 255 caractères"
     * )
     * @Groups("Api:Client")
     */
    private $surname;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(
     *      message="Veuillez rentrer un prénom"
     * )
     * @Assert\Length(
     *      max=255,
     *      maxMessage="Veuillez rentrer moins de 255 caractères"
     * )
     * @Groups("Api:Client")
     */
    private $firstName;

    /**
     * @ORM\Column(type="string")
     * @Assert\NotBlank(
     *      message="Veuillez rentrer un numéro"
     * )
     * @Groups("Api:Client")
     */
    private $phoneNumber;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Groups("Api:Client")
     */
    private $addressFact;

    /**
     * @ORM\Column(type="float")
     * @Groups("Api:Client")
     */
    private $coefficient;

    /**
     * @ORM\Column(type="boolean", nullable=true)
     * @Groups("Api:Client")
     */
    private $type;

    /**
     * @ORM\Column(type="string", nullable=true)
     * @Groups("Api:Client")
     */
    private $role;

    /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Groups("Api:Client")
     */
    private $siret;

    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\Users", inversedBy="clients")
     * @Groups("Api:Client")
     */
    private $commercial;

    /**
     * @ORM\OneToMany(targetEntity="App\Entity\Users", mappedBy="commercial")
     */
    private $clients;

    public function __construct()
    {
        $this->users = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getMail(): ?string
    {
        return $this->mail;
    }

    public function setMail(string $mail): self
    {
        $this->mail = $mail;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getSurname(): ?string
    {
        return $this->surname;
    }

    public function setSurname(string $surname): self
    {
        $this->surname = $surname;

        return $this;
    }

    public function getFirstName(): ?string
    {
        return $this->firstName;
    }

    public function setFirstName(string $firstName): self
    {
        $this->firstName = $firstName;

        return $this;
    }

    public function getPhoneNumber(): ?string
    {
        return $this->phoneNumber;
    }

    public function setPhoneNumber(string $phoneNumber): self
    {
        $this->phoneNumber = $phoneNumber;

        return $this;
    }

    /**
     * @inheritDoc
     */
    public function getRoles()
    {

        return ['ROLE_USER'];

    }

    /**
     * @inheritDoc
     */
    public function getSalt()
    {
        // TODO: Implement getSalt() method.
    }

    /**
     * @inheritDoc
     */
    public function getUsername()
    {
        // TODO: Implement getUsername() method.
    }

    /**
     * @inheritDoc
     */
    public function eraseCredentials()
    {
        // TODO: Implement eraseCredentials() method.
    }

    public function getAddressFact(): ?string
    {
        return $this->addressFact;
    }

    public function setAddressFact(?string $addressFact): self
    {
        $this->addressFact = $addressFact;

        return $this;
    }

    public function getCoefficient(): ?float
    {
        return $this->coefficient;
    }

    public function setCoefficient(float $coefficient): self
    {
        $this->coefficient = $coefficient;

        return $this;
    }

    public function getType(): ?bool
    {
        return $this->type;
    }

    public function setType(?bool $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getRole(): ?string
    {
        return $this->role;
    }

    public function setRole(?string $role): self
    {
        $this->role = $role;

        return $this;
    }

    public function getSiret(): ?string
    {
        return $this->siret;
    }

    public function setSiret(?string $siret): self
    {
        $this->siret = $siret;

        return $this;
    }

    public function getCommercial(): ?self
    {
        return $this->commercial;
    }

    public function setCommercial(?self $commercial): self
    {
        $this->commercial = $commercial;

        return $this;
    }

    /**
     * @return Collection|self[]
     */
    public function getClients(): Collection
    {
        return $this->clients;
    }

    public function addClient(self $client): self
    {
        if (!$this->clients->contains($user)) {
            $this->clients[] = $user;
            $user->setCommercial($this);
        }

        return $this;
    }

    public function removeUser(self $user): self
    {
        if ($this->clients->contains($user)) {
            $this->clients->removeElement($user);
            // set the owning side to null (unless already changed)
            if ($user->getCommercial() === $this) {
                $user->setCommercial(null);
            }
        }

        return $this;
    }



}
