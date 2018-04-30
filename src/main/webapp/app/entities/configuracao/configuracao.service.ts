import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Configuracao } from './configuracao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Configuracao>;

@Injectable()
export class ConfiguracaoService {

    private resourceUrl =  SERVER_API_URL + 'api/configuracaos';

    constructor(private http: HttpClient) { }

    create(configuracao: Configuracao): Observable<EntityResponseType> {
        const copy = this.convert(configuracao);
        return this.http.post<Configuracao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(configuracao: Configuracao): Observable<EntityResponseType> {
        const copy = this.convert(configuracao);
        return this.http.put<Configuracao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Configuracao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Configuracao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Configuracao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Configuracao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Configuracao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Configuracao[]>): HttpResponse<Configuracao[]> {
        const jsonResponse: Configuracao[] = res.body;
        const body: Configuracao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Configuracao.
     */
    private convertItemFromServer(configuracao: Configuracao): Configuracao {
        const copy: Configuracao = Object.assign({}, configuracao);
        return copy;
    }

    /**
     * Convert a Configuracao to a JSON which can be sent to the server.
     */
    private convert(configuracao: Configuracao): Configuracao {
        const copy: Configuracao = Object.assign({}, configuracao);
        return copy;
    }
}
