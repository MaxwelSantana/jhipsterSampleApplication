import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TipoConfiguracao } from './tipo-configuracao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TipoConfiguracao>;

@Injectable()
export class TipoConfiguracaoService {

    private resourceUrl =  SERVER_API_URL + 'api/tipo-configuracaos';

    constructor(private http: HttpClient) { }

    create(tipoConfiguracao: TipoConfiguracao): Observable<EntityResponseType> {
        const copy = this.convert(tipoConfiguracao);
        return this.http.post<TipoConfiguracao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(tipoConfiguracao: TipoConfiguracao): Observable<EntityResponseType> {
        const copy = this.convert(tipoConfiguracao);
        return this.http.put<TipoConfiguracao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TipoConfiguracao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TipoConfiguracao[]>> {
        const options = createRequestOption(req);
        return this.http.get<TipoConfiguracao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TipoConfiguracao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TipoConfiguracao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TipoConfiguracao[]>): HttpResponse<TipoConfiguracao[]> {
        const jsonResponse: TipoConfiguracao[] = res.body;
        const body: TipoConfiguracao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TipoConfiguracao.
     */
    private convertItemFromServer(tipoConfiguracao: TipoConfiguracao): TipoConfiguracao {
        const copy: TipoConfiguracao = Object.assign({}, tipoConfiguracao);
        return copy;
    }

    /**
     * Convert a TipoConfiguracao to a JSON which can be sent to the server.
     */
    private convert(tipoConfiguracao: TipoConfiguracao): TipoConfiguracao {
        const copy: TipoConfiguracao = Object.assign({}, tipoConfiguracao);
        return copy;
    }
}
