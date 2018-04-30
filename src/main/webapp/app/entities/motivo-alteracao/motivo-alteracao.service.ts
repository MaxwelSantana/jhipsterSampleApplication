import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { MotivoAlteracao } from './motivo-alteracao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MotivoAlteracao>;

@Injectable()
export class MotivoAlteracaoService {

    private resourceUrl =  SERVER_API_URL + 'api/motivo-alteracaos';

    constructor(private http: HttpClient) { }

    create(motivoAlteracao: MotivoAlteracao): Observable<EntityResponseType> {
        const copy = this.convert(motivoAlteracao);
        return this.http.post<MotivoAlteracao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(motivoAlteracao: MotivoAlteracao): Observable<EntityResponseType> {
        const copy = this.convert(motivoAlteracao);
        return this.http.put<MotivoAlteracao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MotivoAlteracao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MotivoAlteracao[]>> {
        const options = createRequestOption(req);
        return this.http.get<MotivoAlteracao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MotivoAlteracao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MotivoAlteracao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MotivoAlteracao[]>): HttpResponse<MotivoAlteracao[]> {
        const jsonResponse: MotivoAlteracao[] = res.body;
        const body: MotivoAlteracao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MotivoAlteracao.
     */
    private convertItemFromServer(motivoAlteracao: MotivoAlteracao): MotivoAlteracao {
        const copy: MotivoAlteracao = Object.assign({}, motivoAlteracao);
        return copy;
    }

    /**
     * Convert a MotivoAlteracao to a JSON which can be sent to the server.
     */
    private convert(motivoAlteracao: MotivoAlteracao): MotivoAlteracao {
        const copy: MotivoAlteracao = Object.assign({}, motivoAlteracao);
        return copy;
    }
}
