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
                                        <p class="card-stats-title"><i class="mdi-social-group-add"></i> ����� ��������</p>
                                        <h4 class="card-stats-number">217</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> 7% <span class="green-text text-lighten-5">�� �����</span>
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
                                        <p class="card-stats-title"><i class="mdi-editor-attach-money"></i>�� ���������</p>
                                        <h4 class="card-stats-number">1270</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> 4% <span class="purple-text text-lighten-5">�� ��������� �����</span>
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
                                        <p class="card-stats-title"><i class="mdi-action-trending-up"></i> �� ������</p>
                                        <h4 class="card-stats-number">76</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-up"></i> 2% <span class="blue-grey-text text-lighten-5">�� ��� ���</span>
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
                                        <p class="card-stats-title"><i class="mdi-editor-insert-drive-file"></i>����� ��������</p>
                                        <h4 class="card-stats-number">86</h4>
                                        <p class="card-stats-compare"><i class="mdi-hardware-keyboard-arrow-down"></i> 3% <span class="deep-purple-text text-lighten-5">�� ��������� 3 ���</span>
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
                <h4 class="header">��������� ������</h4>                
              </div>
	<form method="post" action="">
			  <section class="plans-container" id="plans">
                <article class="col s12 m6 l3">
                  <div class="card hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">�����</div>
                      <div class="price"><sup>RUB</sup>10<sub>/���</sub></div>
                      <div class="price-desc">��������� �����</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">������������</li>
                        <li class="collection-item">�������</li>
                        <li class="collection-item">1 ����������</li>
                        <li class="collection-item">�����������</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="1" class="waves-effect waves-light  light-blue btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l3">
                  <div class="card z-depth-1 hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">�����+</div>
                      <div class="price"><sup>RUB</sup>30<sub>/���</sub></div>
                      <div class="price-desc">���������� �����</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">��������� 20 �������</li>
                        <li class="collection-item">������ �������</li>
                        <li class="collection-item">1 ����������</li>
                        <li class="collection-item">������������</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="2" class="waves-effect waves-light  light-blue btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l3">
                  <div class="card hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">���� � �����</div>
                      <div class="price"><sup>RUB</sup>49<sub>/���</sub></div>
                      <div class="price-desc">������ ������� ��� ����������</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">�����������</li>
                        <li class="collection-item">������� ������� �����</li>
                        <li class="collection-item">������ �����������</li>
                        <li class="collection-item">���</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="3" class="waves-effect waves-light  light-blue btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
				<article class="col s12 m6 l3">
                  <div class="card hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">���� �����</div>
                      <div class="price"><sup>RUB</sup>79<sub>/���</sub></div>
                      <div class="price-desc">10% ���������� ����������</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">��������� 150 �������</li>
                        <li class="collection-item">������ ���������</li>
                        <li class="collection-item">������ �������</li>
                        <li class="collection-item">������ �� ���</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="4" class="waves-effect waves-light  light-blue btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
              </section>
			  
				<div class="col s12">
                <h4 class="header">������� ������</h4>                
              </div>
              <section class="plans-container" id="plans">
                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image purple waves-effect">
                      <div class="card-title">�������</div>
                      <div class="price"><sup>RUB</sup>99<sub>/���</sub></div>
                      <div class="price-desc">PREMIUM �����</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">500 �������</li>
                        <li class="collection-item">����������� �� ���</li>
                        <li class="collection-item">������ ��������</li>
                        <li class="collection-item">������ ������ �� ���</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="5" class="waves-effect waves-light  btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l4">
                  <div class="card z-depth-1 hoverable">
                    <div class="card-image cyan waves-effect">
                      <div class="card-title">�������+</div>
                      <div class="price"><sup>RUB</sup>129<sub>/���</sub></div>
                      <div class="price-desc">���������� ����� �������</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">1000 �������</li>
                        <li class="collection-item">������ �� ��� ������</li>
                        <li class="collection-item">����������� �� ������</li>
                        <li class="collection-item">���������� �����������</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="6" class="waves-effect waves-light  btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image green waves-effect">
                      <div class="card-title">����� �������</div>
                      <div class="price"><sup>RUB</sup>149<sub>/���</sub></div>
                      <div class="price-desc">���������� 15% ����������</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">2000 �������</li>
                        <li class="collection-item">������ �� �����</li>
                        <li class="collection-item">����������� ������ �����</li>
                        <li class="collection-item">����������� �������������� � 3D</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="7" class="waves-effect waves-light  btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
              </section>

              <div class="col s12">
                <br><br>
                <div class="divider"></div>
                <h4 class="header">����������� ������</h4>                
              </div>              
             
              <section class="plans-container" id="plans">
                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image light-blue waves-effect">
                      <div class="card-title">������� ����</div>
                      <div class="price"><sup>RUB</sup>229<sub>/���</sub></div>
                      <div class="price-desc">������ ������</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">�������� ������ 3 ������!</li>
                        <li class="collection-item">����������� �����������</li>
                        <li class="collection-item">3 ����������</li>
                        <li class="collection-item">����������� ����������</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="8" class="waves-effect waves-light light-blue btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>
				
                <article class="col s12 m6 l4 ">
                  <div class="card hoverable">
                    <div class="card-image light-blue darken-1 waves-effect">
                      <div class="card-title">�������� ����</div>
                      <div class="price"><sup>RUB</sup>299<sub>/���</sub></div>
                      <div class="price-desc">����� �����������</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">����� - �������� ����������</li>
                        <li class="collection-item">�������� GPS � �������</li>
                        <li class="collection-item">4 ����������</li>
                        <li class="collection-item">�������� �������</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="9" class="waves-effect waves-light light-blue btn">������� ���� �����</button>
                    </div>
                  </div>
                </article>

                <article class="col s12 m6 l4">
                  <div class="card hoverable">
                    <div class="card-image light-blue darken-2 waves-effect">
                      <div class="card-title">����+</div>
                      <div class="price"><sup>RUB</sup>359<sub>/���</sub></div>
                      <div class="price-desc">���������� 20% ����������</div>
                    </div>
                    <div class="card-content">
                      <ul class="collection">
                        <li class="collection-item">������ �������� ������</li>
                        <li class="collection-item">����� ���� ��������</li>
                        <li class="collection-item">5 ���������</li>
                        <li class="collection-item">������ �������� ���� ��� � �������</li>
                      </ul>
                    </div>
                    <div class="card-action center-align">                      
                      <button type="submit" name="useTariff" value="10" class="waves-effect waves-light light-blue btn">������� �����</button>
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