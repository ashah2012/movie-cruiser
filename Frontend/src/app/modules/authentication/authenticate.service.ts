import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

 import * as jwt_decode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';
export const TOKEN_NAME: string = 'jwt_token';


@Injectable()
export class AuthenticateService {


  springEndpoint: string;
  token: string;

  constructor(private httpClient: HttpClient) {
    this.springEndpoint = 'http://localhost:8089/user'
  }

  registerUser(newuser) {
    const url = this.springEndpoint + "/register";
    console.log('newuser from auth service register method', newuser);
    return this.httpClient.post(url, newuser, { responseType: 'text' });
  }

  loginUser(newuser) {
    const url = this.springEndpoint + "/login";
    console.log('newuser from auth service login method', newuser);
    return this.httpClient.post(url, newuser);
  }

  setToken(token: string) {
    return localStorage.setItem(TOKEN_NAME, token);
  }

  getToken(){
    return localStorage.getItem(TOKEN_NAME);
  }

  deleteToken(){
    return localStorage.removeItem(TOKEN_NAME);
  }

  getTokenExpirationDate(token:string): Date {
    const decodedToken  = jwt_decode(token);
    if(decodedToken.exp === undefined) return  null;
    const date = new Date(0);
    date.setUTCSeconds(decodedToken.exp);
    return date;
  }

  isTokenExpired(token?: string): boolean {
    if(!token) token =this.getToken();
    if(!token) return true;
    const date = this.getTokenExpirationDate(token);
    if(date === undefined || date === null ) return false;
    return !(date.valueOf() > new Date().valueOf());

  }

}
