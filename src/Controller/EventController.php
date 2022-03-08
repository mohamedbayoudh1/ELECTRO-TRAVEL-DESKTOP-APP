<?php

namespace App\Controller;

use App\Entity\Event;
use App\Form\EventType;
use App\Repository\EventRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/booking")
 */
class EventController extends AbstractController
{
    // ...

    /**
     * @Route("/calendar", name="event_calendar", methods={"GET"})
     */
    public function calendar(): Response
    {
        return $this->render('events/calendar.html.twig');
    }

    // ...
}