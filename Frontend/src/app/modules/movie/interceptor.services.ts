import { Injectable } from '@angular/core';
import { AuthenticateService } from '../authentication/authenticate.service';

import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http'; 

import {Observable } from 'rxjs/Observable';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

constructor(private auth: AuthenticateService){

}

intercept(request : HttpRequest<any>, next : HttpHandler): Observable<HttpEvent<any>>{
    console.log('In intercpetor');
    request = request.clone({
        setHeaders : {
            Authorization: 'Bearer ' + this.auth.getToken()
        }
    });
    return next.handle(request);
}


}