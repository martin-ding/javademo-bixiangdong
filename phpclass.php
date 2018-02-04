<?php

class Demo1{

	public function __construct(){
		print("Demo1");
	}
}

class Demo2 extends Demo1{
	public function __construct(){
		parent::__construct();
		print("Demo2");
	}
}


$demo2 = new Demo2();

