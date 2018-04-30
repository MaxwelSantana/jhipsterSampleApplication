import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { ConfiguracaoColetor } from './configuracao-coletor.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<ConfiguracaoColetor>;

@Injectable()
export class ConfiguracaoColetorService {

    private resourceUrl =  SERVER_API_URL + 'api/configuracao-coletors';

    constructor(private http: HttpClient) { }

    create(configuracaoColetor: ConfiguracaoColetor): Observable<EntityResponseType> {
        const copy = this.convert(configuracaoColetor);
        return this.http.post<ConfiguracaoColetor>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(configuracaoColetor: ConfiguracaoColetor): Observable<EntityResponseType> {
        const copy = this.convert(configuracaoColetor);
        return this.http.put<ConfiguracaoColetor>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ConfiguracaoColetor>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<ConfiguracaoColetor[]>> {
        const options = createRequestOption(req);
        return this.http.get<ConfiguracaoColetor[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<ConfiguracaoColetor[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: ConfiguracaoColetor = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<ConfiguracaoColetor[]>): HttpResponse<ConfiguracaoColetor[]> {
        const jsonResponse: ConfiguracaoColetor[] = res.body;
        const body: ConfiguracaoColetor[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to ConfiguracaoColetor.
     */
    private convertItemFromServer(configuracaoColetor: ConfiguracaoColetor): ConfiguracaoColetor {
        const copy: ConfiguracaoColetor = Object.assign({}, configuracaoColetor);
        return copy;
    }

    /**
     * Convert a ConfiguracaoColetor to a JSON which can be sent to the server.
     */
    private convert(configuracaoColetor: ConfiguracaoColetor): ConfiguracaoColetor {
        const copy: ConfiguracaoColetor = Object.assign({}, configuracaoColetor);
        return copy;
    }
}
