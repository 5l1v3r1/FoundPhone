<div align="center">
<?php
if($_GET['useTariff'] == true || $_POST['useTariff'] == true && $_SESSION['useTariff']){

//старт
if($_SESSION['useTariff'] == 1){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%A1%D0%A2%D0%90%D0%A0%D0%A2&targets-hint=&default-sum=10&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//старт+
if($_SESSION['useTariff'] == 2){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%A1%D0%A2%D0%90%D0%A0%D0%A2%2B&targets-hint=&default-sum=30&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//будь в курсе
if($_SESSION['useTariff'] == 3){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%91%D0%A3%D0%94%D0%AC+%D0%92+%D0%9A%D0%A3%D0%A0%D0%A1%D0%95&targets-hint=&default-sum=49&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//хочу знать
if($_SESSION['useTariff'] == 4){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%A5%D0%9E%D0%A7%D0%A3+%D0%97%D0%9D%D0%90%D0%A2%D0%AC&targets-hint=&default-sum=79&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//премиум
if($_SESSION['useTariff'] == 5){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%9F%D0%A0%D0%95%D0%9C%D0%98%D0%A3%D0%9C&targets-hint=&default-sum=99&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//премиум+
if($_SESSION['useTariff'] == 6){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%9F%D0%A0%D0%95%D0%9C%D0%98%D0%A3%D0%9C%2B&targets-hint=&default-sum=129&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//супер премиум
if($_SESSION['useTariff'] == 7){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%A1%D0%A3%D0%9F%D0%95%D0%A0+%D0%9F%D0%A0%D0%95%D0%9C%D0%98%D0%A3%D0%9C&targets-hint=&default-sum=149&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//базовый спец
if($_SESSION['useTariff'] == 8){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%91%D0%90%D0%97%D0%9E%D0%92%D0%AB%D0%99+%D0%A1%D0%9F%D0%95%D0%A6&targets-hint=&default-sum=229&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//стандарт спец
if($_SESSION['useTariff'] == 9){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%A1%D0%A2%D0%90%D0%9D%D0%94%D0%90%D0%A0%D0%A2+%D0%A1%D0%9F%D0%95%D0%A6&targets-hint=&default-sum=299&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>
<?php }
//спец+
if($_SESSION['useTariff'] == 10){ ?> 
<iframe frameborder="0" allowtransparency="true" scrolling="no" src="https://money.yandex.ru/quickpay/shop-widget?account=410011025406860&quickpay=shop&payment-type-choice=on&mobile-payment-type-choice=on&writer=seller&targets=%D0%A2%D0%B0%D1%80%D0%B8%D1%84%D0%BD%D1%8B%D0%B9+%D0%BF%D0%BB%D0%B0%D0%BD+%D0%A1%D0%9F%D0%95%D0%A6%2B&targets-hint=&default-sum=359&button-text=01&mail=on&phone=on&successURL=http%3A%2F%2Fwww.fphone.com" width="450" height="198"></iframe>

<?php } }?>
</div>