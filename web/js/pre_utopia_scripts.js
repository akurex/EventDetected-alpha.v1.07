$('*').delegate('body','pagecreate', function(){
	 //checkall for Property Type
	$('#grpPropertyType .checkall').tap(function(event) {
		$('#grpPropertyType input[type="checkbox"]').attr('checked', $('#checkbox_property_type_id').is(':checked')).checkboxradio( "refresh" );
	 });


	$('#frmPage1').validationEngine({
		promptPosition: "centerRight"
	});
	
	$('#frmPage2').validationEngine({
		promptPosition: "centerRight"
	});	
	
	$('#byEmail_label').tap(function(event) {
	  if ($("#byEmail").is(":checked")) 
	   { 	
			$('#req_email').addClass('reqField');
			$('#email').addClass("validate[required,custom[email]]");
	  }
	  else
	   { 
	 		$('#req_email').removeClass('reqField');
			$('#email').removeClass("validate[required,custom[email]]").validationEngine('hide');	
	  }
	});
	
	$('#bySMS_label').tap(function(event) {
	  if ($("#bySMS").is(":checked")) 
	   { 	
		$('#req_mobilephone').addClass('reqField', 500);
		$('#mobilephone').addClass("validate[required]");

		$('#bySMS').attr('checked', 'checked').checkboxradio( "refresh" );
		$('#byMMS').attr('checked', '').checkboxradio( "refresh" );	
	  }
	  else
	   { 
		$('#req_mobilephone').removeClass('reqField', 500);
		$('#mobilephone').removeClass("validate[required]").validationEngine('hide');

		$('#bySMS').attr('checked', '').checkboxradio( "refresh" );
	  }
	});	
	
	$('#byMMS_label').tap(function(event) {
	  if ($("#byMMS").is(":checked")) 
	   { 	
		$('#req_mobilephone').addClass('reqField', 500);
		$('#mobilephone').addClass("validate[required]");

		$('#byMMS').attr('checked', 'checked').checkboxradio( "refresh" );
		$('#bySMS').attr('checked', '').checkboxradio( "refresh" );	
	  }
	  else
	   { 
		$('#req_mobilephone').removeClass('reqField', 500);
		$('#mobilephone').removeClass("validate[required]").validationEngine('hide');

		$('#byMMS').attr('checked', '').checkboxradio( "refresh" );
	  }
	});	

	$('#byPost_label').tap(function(event) {
	  if ($("#byPost").is(":checked")) 
	   { 	
		$('#req_address1').addClass('reqField', 500);
		$('#req_suburb').addClass('reqField', 500);
		$('#req_state').addClass('reqField', 500);
		$('#req_postcode').addClass('reqField', 500);
						
		$('#address1').addClass("validate[required]");
		$('#suburb').addClass("validate[required]");
		$('#state').addClass("validate[required]");
		$('#postcode').addClass("validate[required]");
		$('#byPost').attr('checked', 'checked').checkboxradio( "refresh" );
	  }
	  else
	   { 
		$('#req_address1').removeClass('reqField', 500);
		$('#req_suburb').removeClass('reqField', 500);
		$('#req_state').removeClass('reqField', 500);
		$('#req_postcode').removeClass('reqField', 500);
						
		$('#address1').removeClass("validate[required]").validationEngine('hide');
		$('#suburb').removeClass("validate[required]").validationEngine('hide');
		$('#state').removeClass("validate[required]").validationEngine('hide');
		$('#postcode').removeClass("validate[required]").validationEngine('hide');
		$('#byPost').attr('checked', '').checkboxradio( "refresh" );
	  }
	});	

	
});