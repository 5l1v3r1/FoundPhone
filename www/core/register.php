<div align="center">
<?php
if($_GET['useTariff'] == false || $_POST['useTariff'] == false){

//старт
if($_SERVER['REQUEST_URI'] == '/?register' || $_SERVER['REQUEST_URI'] == '/index.php?register'){ ?> 

<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
                           
						<div class="panel-body">
											
                                <form role="form" method="post" action="?add">
									<hr />
                                   
                                       <br />	
										<div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"  ></i></span>
                                            <input id="reg_fio_i" name="reg_fio_i" type="reg_fio_i" class="form-control"  placeholder="ваше имя" />
                                        </div>
										<div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-user"  ></i></span>
                                            <input id="reg_fio_f" name="reg_fio_f" type="reg_fio_f" class="form-control"  placeholder="ваша фамилия" />
                                        </div>
                                    <hr />
                                   
                                       <br />
										<div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-user-md"  ></i></span>
                                            <input id="reg_email" type="reg_email" name="reg_email" class="form-control" placeholder="ваш Email" />
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock"  ></i></span>
                                            <input id="reg_password" name="reg_password" type="reg_password" class="form-control"  placeholder="ваш пароль" />
                                        </div>
										<div class="form-group input-group">
                                            <span class="input-group-addon"><i class="fa fa-phone-square"  ></i></span>
                                            <input id="reg_phone" name="reg_phone" type="reg_phone" class="form-control"  placeholder="номер телефона" />
                                        </div>
                                    <div class="form-group">
                                    </div>
                                     
                                    <button class="btn btn-primary">РЕГИСТРАЦИЯ</button> 
                                    <hr />
                                </form>							
								
                        </div>
                </div>

<?php } }?> 

</div>