<?php
namespace App\Controller;

use App\Entity\PropertySearch;
use App\Form\PropertySearchType;
use App\Form\SponsorType;
use App\Entity\Sponsor;
use App\Entity\Event;
use App\Form\EventType;

use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


class IndexController extends AbstractController
{
    /**
     *@Route("/",name="sponsor_list")
     */
    public function home(Request $request)
    {
        $propertySearch = new PropertySearch();
        $form = $this->createForm(PropertySearchType::class,$propertySearch);
        $form->handleRequest($request);
        //initialement le tableau des sponseur est vide,
        //c.a.d on affiche les sponseurs que lorsque l'utilisateur
        //clique sur le bouton rechercher
        $sponsors= [];

        if($form->isSubmitted() && $form->isValid()) {
            //on récupère le nom sponsor tapé dans le formulaire
            $nom = $propertySearch->getNom();
            if ($nom!="")
                //si on a fourni un nom  on affiche tous les sponsors ayant ce nom
 $sponsors= $this->getDoctrine()->getRepository(Sponsor::class)->findBy(['nom' => $nom] );
 else
 //si aucun nom n'est fourni on affiche tous les sponseurs
 $sponsors= $this->getDoctrine()->getRepository(Sponsor::class)->findAll();
 }
        return $this->render('articles/index.html.twig',[ 'form' =>$form->createView(), 'sponsors' => $sponsors]);
 }


    /**
     * @Route("/sponsor/new", name="new_sponsor")
     * Method({"GET", "POST"})
     */
    public function new(Request $request) {
        $sponsor = new Sponsor();
        $form = $this->createForm(SponsorType::class,$sponsor);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()) {
           // $sponsor = $form->getData();
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($sponsor);
            $entityManager->flush();
            return $this->redirectToRoute('sponsor_list');
        }
        return $this->render('articles/new.html.twig',['form' => $form->createView()]);
 }

    /**
     * @Route("/sponsor/{id}", name="sponsor_show")
     */
    public function show($id) {
        $sponsor = $this->getDoctrine()->getRepository(Sponsor::class) ->find($id);
        return $this->render('articles/show.html.twig',
            array('sponsor' => $sponsor));
    }

    /**
     * @Route("/sponsor/edit/{id}", name="edit_sponsor")
     * Method({"GET", "POST"})
     */
    public function edit(Request $request, $id)
    {
        $sponsor = new Sponsor();
        $sponsor = $this->getDoctrine()->getRepository(Sponsor::class)->find($id);

        $form = $this->createForm(SponsorType::class, $sponsor);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->flush();

            return $this->redirectToRoute('sponsor_list');
        }
        return $this->render('articles/edit.html.twig', ['form' => $form->createView()]);
    }

/**
 * @Route("/sponsor/delete/{id}",name="delete_sponsor")
 * @Method({"DELETE"})
  */
    public function delete(Request $request, $id) {
        $sponsor = $this->getDoctrine()->getRepository(Sponsor::class)->find($id);

 $entityManager = $this->getDoctrine()->getManager();
 $entityManager->remove($sponsor);
 $entityManager->flush();

 $response = new Response();
 $response->send();
 return $this->redirectToRoute('sponsor_list');
 }
    /**
     * @Route("/event/newEvent", name="new_event")
     * Method({"GET", "POST"})
     */
    public function newEvent(Request $request) {
        $event = new event();
        $form = $this->createForm(EventType::class,$event);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()) {
            $event = $form->getData();
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($event);
            $entityManager->flush();
        }
        return $this->render('articles/newEvent.html.twig',['form'=>
            $form->createView()]);
    }



}