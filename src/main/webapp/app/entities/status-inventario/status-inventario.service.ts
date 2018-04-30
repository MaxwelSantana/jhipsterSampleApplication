import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { StatusInventario } from './status-inventario.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<StatusInventario>;

@Injectable()
export class StatusInventarioService {

    private resourceUrl =  SERVER_API_URL + 'api/status-inventarios';

    constructor(private http: HttpClient) { }

    create(statusInventario: StatusInventario): Observable<EntityResponseType> {
        const copy = this.convert(statusInventario);
        return this.http.post<StatusInventario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(statusInventario: StatusInventario): Observable<EntityResponseType> {
        const copy = this.convert(statusInventario);
        return this.http.put<StatusInventario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<StatusInventario>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<StatusInventario[]>> {
        const options = createRequestOption(req);
        return this.http.get<StatusInventario[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<StatusInventario[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: StatusInventario = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<StatusInventario[]>): HttpResponse<StatusInventario[]> {
        const jsonResponse: StatusInventario[] = res.body;
        const body: StatusInventario[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to StatusInventario.
     */
    private convertItemFromServer(statusInventario: StatusInventario): StatusInventario {
        const copy: StatusInventario = Object.assign({}, statusInventario);
        return copy;
    }

    /**
     * Convert a StatusInventario to a JSON which can be sent to the server.
     */
    private convert(statusInventario: StatusInventario): StatusInventario {
        const copy: StatusInventario = Object.assign({}, statusInventario);
        return copy;
    }
}
