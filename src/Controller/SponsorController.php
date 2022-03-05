<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class SponsorController extends AbstractController
{
    /**
     * @Route("/sponsor", name="sponsor")
     */
    public function index(): Response
    {
        return $this->render('sponsor/index.html.twig', [
            'controller_name' => 'SponsorController',
        ]);
    }
}
