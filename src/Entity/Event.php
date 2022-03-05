<?php

namespace App\Entity;

use App\Repository\EventRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=EventRepository::class)
 */
class Event
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $titre;

    /**
     * @ORM\Column(type="text")
     */
    private $description;

    /**
     * @ORM\OneToMany(targetEntity=Sponsor::class, mappedBy="event")
     */
    private $sponseur;

    public function __construct()
    {
        $this->sponseur = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->titre;
    }

    public function setTitre(string $titre): self
    {
        $this->titre = $titre;

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

    /**
     * @return Collection<int, Sponsor>
     */
    public function getSponseur(): Collection
    {
        return $this->sponseur;
    }

    public function addSponseur(Sponsor $sponseur): self
    {
        if (!$this->sponseur->contains($sponseur)) {
            $this->sponseur[] = $sponseur;
            $sponseur->setEvent($this);
        }

        return $this;
    }

    public function removeSponseur(Sponsor $sponseur): self
    {
        if ($this->sponseur->removeElement($sponseur)) {
            // set the owning side to null (unless already changed)
            if ($sponseur->getEvent() === $this) {
                $sponseur->setEvent(null);
            }
        }

        return $this;
    }
}
