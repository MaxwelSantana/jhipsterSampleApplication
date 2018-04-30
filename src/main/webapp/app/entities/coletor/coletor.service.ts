import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Coletor } from './coletor.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Coletor>;

@Injectable()
export class ColetorService {

    private resourceUrl =  SERVER_API_URL + 'api/coletors';

    constructor(private http: HttpClient) { }

    create(coletor: Coletor): Observable<EntityResponseType> {
        const copy = this.convert(coletor);
        return this.http.post<Coletor>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(coletor: Coletor): Observable<EntityResponseType> {
        const copy = this.convert(coletor);
        return this.http.put<Coletor>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Coletor>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Coletor[]>> {
        const options = createRequestOption(req);
        return this.http.get<Coletor[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Coletor[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Coletor = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Coletor[]>): HttpResponse<Coletor[]> {
        const jsonResponse: Coletor[] = res.body;
        const body: Coletor[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Coletor.
     */
    private convertItemFromServer(coletor: Coletor): Coletor {
        const copy: Coletor = Object.assign({}, coletor);
        return copy;
    }

    /**
     * Convert a Coletor to a JSON which can be sent to the server.
     */
    private convert(coletor: Coletor): Coletor {
        const copy: Coletor = Object.assign({}, coletor);
        return copy;
    }
}
