import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CodigoCustomizavel } from './codigo-customizavel.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CodigoCustomizavel>;

@Injectable()
export class CodigoCustomizavelService {

    private resourceUrl =  SERVER_API_URL + 'api/codigo-customizavels';

    constructor(private http: HttpClient) { }

    create(codigoCustomizavel: CodigoCustomizavel): Observable<EntityResponseType> {
        const copy = this.convert(codigoCustomizavel);
        return this.http.post<CodigoCustomizavel>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(codigoCustomizavel: CodigoCustomizavel): Observable<EntityResponseType> {
        const copy = this.convert(codigoCustomizavel);
        return this.http.put<CodigoCustomizavel>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CodigoCustomizavel>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CodigoCustomizavel[]>> {
        const options = createRequestOption(req);
        return this.http.get<CodigoCustomizavel[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CodigoCustomizavel[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CodigoCustomizavel = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CodigoCustomizavel[]>): HttpResponse<CodigoCustomizavel[]> {
        const jsonResponse: CodigoCustomizavel[] = res.body;
        const body: CodigoCustomizavel[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CodigoCustomizavel.
     */
    private convertItemFromServer(codigoCustomizavel: CodigoCustomizavel): CodigoCustomizavel {
        const copy: CodigoCustomizavel = Object.assign({}, codigoCustomizavel);
        return copy;
    }

    /**
     * Convert a CodigoCustomizavel to a JSON which can be sent to the server.
     */
    private convert(codigoCustomizavel: CodigoCustomizavel): CodigoCustomizavel {
        const copy: CodigoCustomizavel = Object.assign({}, codigoCustomizavel);
        return copy;
    }
}
