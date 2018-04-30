import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Cadastro } from './cadastro.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Cadastro>;

@Injectable()
export class CadastroService {

    private resourceUrl =  SERVER_API_URL + 'api/cadastros';

    constructor(private http: HttpClient) { }

    create(cadastro: Cadastro): Observable<EntityResponseType> {
        const copy = this.convert(cadastro);
        return this.http.post<Cadastro>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(cadastro: Cadastro): Observable<EntityResponseType> {
        const copy = this.convert(cadastro);
        return this.http.put<Cadastro>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Cadastro>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Cadastro[]>> {
        const options = createRequestOption(req);
        return this.http.get<Cadastro[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Cadastro[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Cadastro = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Cadastro[]>): HttpResponse<Cadastro[]> {
        const jsonResponse: Cadastro[] = res.body;
        const body: Cadastro[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Cadastro.
     */
    private convertItemFromServer(cadastro: Cadastro): Cadastro {
        const copy: Cadastro = Object.assign({}, cadastro);
        return copy;
    }

    /**
     * Convert a Cadastro to a JSON which can be sent to the server.
     */
    private convert(cadastro: Cadastro): Cadastro {
        const copy: Cadastro = Object.assign({}, cadastro);
        return copy;
    }
}
