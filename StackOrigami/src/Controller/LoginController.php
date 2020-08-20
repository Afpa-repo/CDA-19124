<?php
namespace App\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Security\Core\User\UserProviderInterface;
use Symfony\Component\Security\Guard\AbstractGuardAuthenticator;
use Symfony\Component\Security\Core\Exception\AuthenticationException;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;


class LoginController extends AbstractGuardAuthenticator
{
   private $passwordEncoder;
   public function __construct(UserPasswordEncoderInterface $passwordEncoder)
   {
       $this->passwordEncoder = $passwordEncoder;
   }
   public function supports(Request $request)
   {
       return $request->get("_route") === "api_login" && $request->isMethod("POST");
   }
   public function getCredentials(Request $request)
   {
       return [
           'mail' => $request->request->get("mail"),
           'password' => $request->request->get("password")
       ];
   }
   public function getUser($credentials, UserProviderInterface $userProvider)
   {
       return $userProvider->loadUserByUsername($credentials['mail']);
   }
   public function checkCredentials($credentials, UserInterface $user)
   {
	   var_dump($user);die;
       return $this->passwordEncoder->isPasswordValid($user, $credentials['password']);
   }
   public function onAuthenticationFailure(Request $request, AuthenticationException $exception)
   {
       return new JsonResponse([
           'error' => $exception->getMessageKey()
       ], 400);
   }
   public function onAuthenticationSuccess(Request $request, TokenInterface $token, $providerKey)
   {
       return new JsonResponse([
           'result' => true
       ]);
   }
   public function start(Request $request, AuthenticationException $authException = null)
   {
       return new JsonResponse([
           'error' => 'Access Denied'
       ]);
   }
   public function supportsRememberMe()
   {
       return false;
   }
}