import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-master',
  templateUrl: './register-master.component.html',
  styleUrls: ['./register-master.component.css']
})
export class RegisterMasterComponent implements OnInit {

  apiURL = 'http://localhost:8080/api';

   

  formRegistroMestre = new FormGroup({
    anosExperiencia : new FormControl(null),
    qtdCampanhas: new FormControl(null),
    resumoMestre: new FormControl(null,[Validators.required,Validators.minLength(140)])});

  constructor(private http : HttpClient) { }

  ngOnInit(): void {
  }

  onSubmit() {

    if(this.formRegistroMestre.valid)
    {
      var mestre = {
      anosExperiencia: this.formRegistroMestre.get('anosExperiencia').value,
      qtdCampanhas: this.formRegistroMestre.get('qtdCampanhas').value
    }

    console.log(mestre);

    var request = this.http.post(`${ this.apiURL }/mestre` , mestre).toPromise();

    request.then((data)=>{

      var jsonInfo = JSON.stringify(data);
      //console.log(jsonInfo);
      var Info = JSON.parse(jsonInfo);
      console.log(Info.response.status);

      this.formRegistroMestre.get('anosExperiencia').setValue(0);
      this.formRegistroMestre.get('qtdCampanhas').setValue(0);
      
      this.formRegistroMestre.get('anosExperiencia').untouched;
      this.formRegistroMestre.get('qtdCampanhas').untouched;
      

  
  
    }).catch((error)=>{
      
      console.log("Promise rejected with " + JSON.stringify(error));
      var Info = JSON.parse(JSON.stringify(error));
      console.log(Info);
      
    })
      
  
     
    }

    else{
      console.log("erros");
    }
  }


}
