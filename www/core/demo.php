<?php


?>


  <link href="demo/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="demo/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- Custome CSS-->    
  <link href="demo/css/custom/custom.css" type="text/css" rel="stylesheet" media="screen,projection">


  <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="demo/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="demo/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="demo/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">

 
  <!-- ///////////////////////////////////  TOP BAR WODGET  /////////////////////////////////// -->
			<div class="row">
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content  green white-text">
                                        <p class="card-stats-title"><i class="mdi-social-group-add"></i> Новых клиентов</p>
                                        <h4 class="card-stats-number">217</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> 7% <span class="green-text text-lighten-5">за месяц</span>
                                        </p>
                                    </div>
                                    <div class="card-action  green darken-2">
                                        <div id="clients-bar" class="center-align"><canvas width="227" height="25" style="display: inline-block; width: 227px; height: 25px; vertical-align: top;"></canvas></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content purple white-text">
                                        <p class="card-stats-title"><i class="mdi-editor-attach-money"></i>Мы привлекли</p>
                                        <h4 class="card-stats-number">1270</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> 4% <span class="purple-text text-lighten-5">за последний месяц</span>
                                        </p>
                                    </div>
                                    <div class="card-action purple darken-2">
                                        <div id="sales-compositebar" class="center-align"><canvas width="214" height="25" style="display: inline-block; width: 214px; height: 25px; vertical-align: top;"></canvas></div>

                                    </div>
                                </div>
                            </div>                            
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content blue-grey white-text">
                                        <p class="card-stats-title"><i class="mdi-action-trending-up"></i> Мы растем</p>
                                        <h4 class="card-stats-number">76</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> 2% <span class="blue-grey-text text-lighten-5">за два дня</span>
                                        </p>
                                    </div>
                                    <div class="card-action blue-grey darken-2">
                                        <div id="profit-tristate" class="center-align"><canvas width="227" height="25" style="display: inline-block; width: 227px; height: 25px; vertical-align: top;"></canvas></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content deep-purple white-text">
                                        <p class="card-stats-title"><i class="mdi-editor-insert-drive-file"></i>Новых клиентов</p>
                                        <h4 class="card-stats-number">86</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-down"></i> 3% <span class="deep-purple-text text-lighten-5">за последние 3 дня</span>
                                        </p>
                                    </div>
                                    <div class="card-action  deep-purple darken-2">
                                        <div id="invoice-line" class="center-align"><canvas width="187" height="25" style="display: inline-block; width: 187px; height: 25px; vertical-align: top;"></canvas></div>
                                    </div>
                                </div>
                            </div>
                        </div>
	<!-- ////////////////////////////////////  END TOP BAR WIDGET  //////////////////////////////// -->
	<div class="row">
              <div class="col s12">
                <h4 class="header">СТАРТОВЫЕ ПАКЕТЫ</h4>                
              </div>
	<form method="post" action="">
			  <section class="plans-container" id="plans">
                <article class="col s12 m6 l3">
                  <div class="card hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">СТАРТ</div>
                      <div class="price"><sup>RUB</sup>10<sub>/мес</sub></div>
                      <div class="price-desc">стартовый тариф</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">Отслеживание</li>
                        <li class="collection-item">Кабинет</li>
                        <li class="collection-item">1 устройство</li>
                        <li class="collection-item">Уведомления</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="1" class="waves-effect waves-light  light-blue btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l3">
                  <div class="card z-depth-1 hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">СТАРТ+</div>
                      <div class="price"><sup>RUB</sup>30<sub>/мес</sub></div>
                      <div class="price-desc">популярный тариф</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">Последние 20 поинтов</li>
                        <li class="collection-item">Статус батареи</li>
                        <li class="collection-item">1 устройство</li>
                        <li class="collection-item">Отслеживание</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="2" class="waves-effect waves-light  light-blue btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l3">
                  <div class="card hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">БУДЬ В КУРСЕ</div>
                      <div class="price"><sup>RUB</sup>49<sub>/мес</sub></div>
                      <div class="price-desc">лучший вариант для наблюдения</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">Уведомления</li>
                        <li class="collection-item">Локация сотовых вышек</li>
                        <li class="collection-item">Датчик осещенности</li>
                        <li class="collection-item">СМС</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="3" class="waves-effect waves-light  light-blue btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
				<article class="col s12 m6 l3">
                  <div class="card hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">ХОЧУ ЗНАТЬ</div>
                      <div class="price"><sup>RUB</sup>79<sub>/мес</sub></div>
                      <div class="price-desc">10% ежемесячно везвращаем</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">Последние 150 поинтов</li>
                        <li class="collection-item">Данные контактов</li>
                        <li class="collection-item">Данные вызовов</li>
                        <li class="collection-item">Отчеты по СМС</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="4" class="waves-effect waves-light  light-blue btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
              </section>
			  
				<div class="col s12">
                <h4 class="header">ПРЕМИУМ ПАКЕТЫ</h4>                
              </div>
              <section class="plans-container" id="plans">
                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image purple waves-effect">
                      <div class="card-title">ПРЕМИУМ</div>
                      <div class="price"><sup>RUB</sup>99<sub>/мес</sub></div>
                      <div class="price-desc">PREMIUM опции</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">500 поинтов</li>
                        <li class="collection-item">Уведомления на СМС</li>
                        <li class="collection-item">Данные сенсоров</li>
                        <li class="collection-item">Полные отчеты по СМС</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="5" class="waves-effect waves-light  btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l4">
                  <div class="card z-depth-1 hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">ПРЕМИУМ+</div>
                      <div class="price"><sup>RUB</sup>129<sub>/мес</sub></div>
                      <div class="price-desc">популярный среди ПРЕМИУМ</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">1000 поинтов</li>
                        <li class="collection-item">Данные за две недели</li>
                        <li class="collection-item">Уведомления на вызовы</li>
                        <li class="collection-item">Управление устройством</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="6" class="waves-effect waves-light  btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image green waves-effect">
                      <div class="card-title">СУПЕР ПРЕМИУМ</div>
                      <div class="price"><sup>RUB</sup>149<sub>/мес</sub></div>
                      <div class="price-desc">возвращаем 15% ежемесячно</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">2000 поинтов</li>
                        <li class="collection-item">данные за месяц</li>
                        <li class="collection-item">Возможность запись аудио</li>
                        <li class="collection-item">Определение местоположение в 3D</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="7" class="waves-effect waves-light  btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
              </section>

              <div class="col s12">
                <br><br>
                <div class="divider"></div>
                <h4 class="header">СПЕЦИАЛЬНЫЕ ПАКЕТЫ</h4>                
              </div>              
             
              <section class="plans-container" id="plans">
                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image light-blue waves-effect">
                      <div class="card-title">БАЗОВЫЙ СПЕЦ</div>
                      <div class="price"><sup>RUB</sup>229<sub>/мес</sub></div>
                      <div class="price-desc">полный доступ</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">Хранение данных 3 месяца!</li>
                        <li class="collection-item">Безлимитные уведомления</li>
                        <li class="collection-item">3 устройства</li>
                        <li class="collection-item">Возможность блокировки</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="8" class="waves-effect waves-light light-blue btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l4 ">
                  <div class="card hoverable">
                    <div class="card-image light-blue darken-1 waves-effect">
                      <div class="card-title">СТАНДАРТ СПЕЦ</div>
                      <div class="price"><sup>RUB</sup>299<sub>/мес</sub></div>
                      <div class="price-desc">супер предложения</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">Опция - контроль устройства</li>
                        <li class="collection-item">Привязка GPS к локации</li>
                        <li class="collection-item">4 устройства</li>
                        <li class="collection-item">Контроль вызовов</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="9" class="waves-effect waves-light light-blue btn">ВЫБРАТЬ ЭТОТ ТАРИФ</button>
                    </div>
                  </div>
                </article>

                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image light-blue darken-2 waves-effect">
                      <div class="card-title">СПЕЦ+</div>
                      <div class="price"><sup>RUB</sup>359<sub>/мес</sub></div>
                      <div class="price-desc">возвращаем 20% ежемесячно</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">Вечное хранение данных</li>
                        <li class="collection-item">Опция Фейс контроль</li>
                        <li class="collection-item">5 устройств</li>
                        <li class="collection-item">Полный контроль всех СМС и вызовов</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="10" class="waves-effect waves-light light-blue btn">Выбрать тариф</button>
                    </div>
                  </div>
                </article>
              </section>
    </form>
          </div>
	<!--    ///////////////////////  END PRICING ////////////////////      -->
			
			 <!-- INCLUDED PLUGIN CSS ON THIS PAGE -->
  <link href="demo/js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="demo/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="demo/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
  <!-- jQuery Library -->
    <script type="text/javascript" src="demo/js/plugins/jquery-1.11.2.min.js"></script>    
    <!--materialize js-->
    <script type="text/javascript" src="demo/js/materialize.min.js"></script>
    <!--prism-->
    <script type="text/javascript" src="demo/js/plugins/prism/prism.js"></script>
    <!--scrollbar-->
    <script type="text/javascript" src="demo/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    
    <!-- chartjs -->
    <script type="text/javascript" src="demo/js/plugins/chartjs/chart.min.js"></script>
    <script type="text/javascript" src="demo/js/plugins/chartjs/chart-script.js"></script>

    <!-- sparkline -->
    <script type="text/javascript" src="demo/js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="demo/js/plugins/sparkline/sparkline-script.js"></script>

    <!-- chartist -->
    <script type="text/javascript" src="demo/js/plugins/chartist-js/chartist.min.js"></script>   
    
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="demo/js/plugins.min.js"></script>
    <!--custom-script.js - Add your own theme custom JS-->
    <script type="text/javascript" src="demo/js/custom-script.js"></script> 
    
    <!--plugins.js - Some Specific JS codes for Plugin Settings-->
    <script type="text/javascript" src="demo/js/plugins.js"></script>