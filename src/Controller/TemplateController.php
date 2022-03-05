<?php

namespace App\Controller;

use App\Repository\ActiviteRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Hotel;
use App\Repository\HotelRepository;
use App\Repository\RestoRepo;

class TemplateController extends AbstractController
{
    /**
     * @Route("/template", name="template")
     */
    public function index(): Response
    {
        return $this->render('template/index.html.twig', [
            'controller_name' => 'TemplateController',
        ]);
    }





    /**
     * @Route("/templateback", name="templateback")
     */
    public function indexback(): Response
    {
        return $this->render('base-back.html.twig', [
            'controller_name' => 'TemplateController',

        ]);
    }

    /**
     * @Route("/templatefront", name="templateback")
     */
    public function indexback2(): Response
    {
        return $this->render('base-front.html.twig', [
            'controller_name' => 'TemplateController',

        ]);
    }





}
