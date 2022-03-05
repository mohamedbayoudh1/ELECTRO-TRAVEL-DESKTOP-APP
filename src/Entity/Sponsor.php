<?php

namespace App\Entity;

use App\Repository\SponsorRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass=SponsorRepository::class)
 */
class Sponsor
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     * min = 5,
     * max = 50,
     * minMessage = "Le nom d'un sponseur doit comporter au moins {{ limit }} caractères",
     * maxMessage = "Le nom d'un sponseur doit comporter au plus {{ limit }} caractères"
     * )
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $num;

    /**
     * @ORM\Column(type="float")
     * @Assert\NotEqualTo(
     * value = 0,
     * message = "contribution ne doit pas être égal à 0 "
     * )
     */
    private $contribution;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $evenement;

    /**
     * @ORM\ManyToOne(targetEntity=Event::class, inversedBy="sponseur")
     */
    private $event;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getNum(): ?string
    {
        return $this->num;
    }

    public function setNum(string $num): self
    {
        $this->num = $num;

        return $this;
    }

    public function getContribution(): ?float
    {
        return $this->contribution;
    }

    public function setContribution(float $contribution): self
    {
        $this->contribution = $contribution;

        return $this;
    }

    public function getEvenement(): ?string
    {
        return $this->evenement;
    }

    public function setEvenement(string $evenement): self
    {
        $this->evenement = $evenement;

        return $this;
    }

    public function getEvent(): ?Event
    {
        return $this->event;
    }

    public function setEvent(?Event $event): self
    {
        $this->event = $event;

        return $this;
    }
}
