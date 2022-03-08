<?php
namespace App\Entity;
use Doctrine\ORM\Mapping as ORM;
class EventSearch
{
    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\Event")
     */
    private $event;
    public function getEvent(): ?Event
    {
        return $this->event;
    }
    public function setEvent(?Event $event): self
    {
        $this->event = $event;
        return $this;
    } }