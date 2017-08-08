<?php
	
	$page = _get("page");
	if(!$page||$page<1) {
		index("?s=".$s."&page=1");
	}

	$pages = ceil(count($keys)/$config['keys']['perPage']);
	
	if($page>$pages&&$pages!=0) {
		index("?s=".$s."&page=".$pages);
	}
			
	if(count($keys)==0) { $doc.='
		<div style="padding: 5px;">There are no keys.</div>
	'; } else {
	
	$doc.='
	
	<style>
	
		#content {
			padding: 30px 30px;
		}
		
		#titles {
			border-bottom: 1px solid rgb(150, 200, 0);
		}
	
		#content .first,
		#content .second,
		#content .third,
		#content .fourth {
			padding: 3px 5px;
		}
		
		#content .first {
			width: 70px;
		}
		
		#content .second {
			width: 340px;
		}
		
		#content .third {
			width: 270px;
		}
		
		#content .fourth {
			width: 20px;
		}
	
		#content img {
			width: 14px;
			height: 14px;
			padding: 3px 3px;
			position: relative;
			top: 1px;
		}
	
		#content .colored {
			background-color: rgba(0, 0, 0, 0.05);
		}
		
		#content input[type="submit"] {
			margin-bottom: 5px;
			color: inherit;
			background-color: rgb(100, 100, 255);
			padding: 5px 8px;
			font-family: \'bebas_neueregular\';
			font-size: 20px;
			letter-spacing: 1px;
		}
		
		#content input[type="submit"]:hover {
			background-color: rgb(25, 156, 235);
		}
		
	</style>

	<div id="content">
		<form action="?s='.$s.'&page='.$page.'" method="post">
			<input type="text" style="display: none;" name="action" value="addkey"></input>
			<input class="right pointer" type="submit" value="Add Key"></input>
			<div class="clear"></div>
		</form>
		
		<div id="titles">
			<div class="first left">
				ID
			</div>
			<div class="second left">
				Key
			</div>
			<div class="third left">
				Created
			</div>
			<div class="fourth left">
				#
			</div>
			
			<div class="clear"></div>
		</div>
		';

		$color = false;
		for($i=$config['keys']['perPage']*($page-1); $i<=$config['keys']['perPage']*($page)-1; $i++) { if($i>=count($keys)) { break; } $doc.='
			<div class="element'.($color ? " colored" : "").'">
				<div class="first left">
					'.$keys[$i]['id'].'
				</div>
				<div class="second left">
					'.$keys[$i]['key'].'
				</div>
				<div class="third left">
					'.$keys[$i]['created'].'
				</div>
				<div class="fourth left">
					<img class="left pointer submitFormDelete" src="grafik/images/remove.png" alt="'.$keys[$i]['id'].'" title="Delete"></img>
				</div>
				<div class="clear"></div>
			</div>
		'; if(!$color) { $color = true; } else { $color = false; } } $doc.='
		
		<form id="form" action="?s='.$s.'&page='.$page.'" method="post">
			<input type="text" style="display: none;" name="action" value=""></input>
			<input type="text" style="display: none;" name="id" value=""></input>
		</form>
		
		'; if($pages!=1) { $doc.='
			<div style="padding-top: 20px">
				<div class="left" style="width: 80px; min-height: 1px;">
					'; if($page!=1) { $doc.='
						<a href="?s='.$s.'&page='.($page-1).'">previous</a>
					'; } $doc.='
				</div>
				<div class="left" style="width: 580px; min-height: 1px; text-align: center;">
					Page: '.$page.'/'.$pages.'
				</div>
				<div class="left" style="width: 80px; min-height: 1px; text-align: right;">
					'; if($page!=$pages) { $doc.='
						<a href="?s='.$s.'&page='.($page+1).'">next</a>
					'; } $doc.='
				</div>
				<div class="clear"></div>
			</div>
		'; } $doc.='
		
		
	</div>
	
	';
	
	}

?>