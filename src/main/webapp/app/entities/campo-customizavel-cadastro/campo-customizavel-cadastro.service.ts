import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CampoCustomizavelCadastro } from './campo-customizavel-cadastro.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CampoCustomizavelCadastro>;

@Injectable()
export class CampoCustomizavelCadastroService {

    private resourceUrl =  SERVER_API_URL + 'api/campo-customizavel-cadastros';

    constructor(private http: HttpClient) { }

    create(campoCustomizavelCadastro: CampoCustomizavelCadastro): Observable<EntityResponseType> {
        const copy = this.convert(campoCustomizavelCadastro);
        return this.http.post<CampoCustomizavelCadastro>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(campoCustomizavelCadastro: CampoCustomizavelCadastro): Observable<EntityResponseType> {
        const copy = this.convert(campoCustomizavelCadastro);
        return this.http.put<CampoCustomizavelCadastro>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CampoCustomizavelCadastro>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CampoCustomizavelCadastro[]>> {
        const options = createRequestOption(req);
        return this.http.get<CampoCustomizavelCadastro[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CampoCustomizavelCadastro[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CampoCustomizavelCadastro = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CampoCustomizavelCadastro[]>): HttpResponse<CampoCustomizavelCadastro[]> {
        const jsonResponse: CampoCustomizavelCadastro[] = res.body;
        const body: CampoCustomizavelCadastro[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CampoCustomizavelCadastro.
     */
    private convertItemFromServer(campoCustomizavelCadastro: CampoCustomizavelCadastro): CampoCustomizavelCadastro {
        const copy: CampoCustomizavelCadastro = Object.assign({}, campoCustomizavelCadastro);
        return copy;
    }

    /**
     * Convert a CampoCustomizavelCadastro to a JSON which can be sent to the server.
     */
    private convert(campoCustomizavelCadastro: CampoCustomizavelCadastro): CampoCustomizavelCadastro {
        const copy: CampoCustomizavelCadastro = Object.assign({}, campoCustomizavelCadastro);
        return copy;
    }
}
