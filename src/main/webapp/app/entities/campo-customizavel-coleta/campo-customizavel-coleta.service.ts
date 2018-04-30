import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CampoCustomizavelColeta } from './campo-customizavel-coleta.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CampoCustomizavelColeta>;

@Injectable()
export class CampoCustomizavelColetaService {

    private resourceUrl =  SERVER_API_URL + 'api/campo-customizavel-coletas';

    constructor(private http: HttpClient) { }

    create(campoCustomizavelColeta: CampoCustomizavelColeta): Observable<EntityResponseType> {
        const copy = this.convert(campoCustomizavelColeta);
        return this.http.post<CampoCustomizavelColeta>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(campoCustomizavelColeta: CampoCustomizavelColeta): Observable<EntityResponseType> {
        const copy = this.convert(campoCustomizavelColeta);
        return this.http.put<CampoCustomizavelColeta>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CampoCustomizavelColeta>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CampoCustomizavelColeta[]>> {
        const options = createRequestOption(req);
        return this.http.get<CampoCustomizavelColeta[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CampoCustomizavelColeta[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CampoCustomizavelColeta = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CampoCustomizavelColeta[]>): HttpResponse<CampoCustomizavelColeta[]> {
        const jsonResponse: CampoCustomizavelColeta[] = res.body;
        const body: CampoCustomizavelColeta[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CampoCustomizavelColeta.
     */
    private convertItemFromServer(campoCustomizavelColeta: CampoCustomizavelColeta): CampoCustomizavelColeta {
        const copy: CampoCustomizavelColeta = Object.assign({}, campoCustomizavelColeta);
        return copy;
    }

    /**
     * Convert a CampoCustomizavelColeta to a JSON which can be sent to the server.
     */
    private convert(campoCustomizavelColeta: CampoCustomizavelColeta): CampoCustomizavelColeta {
        const copy: CampoCustomizavelColeta = Object.assign({}, campoCustomizavelColeta);
        return copy;
    }
}
