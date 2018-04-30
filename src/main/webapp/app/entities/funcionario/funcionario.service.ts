import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Funcionario } from './funcionario.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Funcionario>;

@Injectable()
export class FuncionarioService {

    private resourceUrl =  SERVER_API_URL + 'api/funcionarios';

    constructor(private http: HttpClient) { }

    create(funcionario: Funcionario): Observable<EntityResponseType> {
        const copy = this.convert(funcionario);
        return this.http.post<Funcionario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(funcionario: Funcionario): Observable<EntityResponseType> {
        const copy = this.convert(funcionario);
        return this.http.put<Funcionario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Funcionario>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Funcionario[]>> {
        const options = createRequestOption(req);
        return this.http.get<Funcionario[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Funcionario[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Funcionario = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Funcionario[]>): HttpResponse<Funcionario[]> {
        const jsonResponse: Funcionario[] = res.body;
        const body: Funcionario[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Funcionario.
     */
    private convertItemFromServer(funcionario: Funcionario): Funcionario {
        const copy: Funcionario = Object.assign({}, funcionario);
        return copy;
    }

    /**
     * Convert a Funcionario to a JSON which can be sent to the server.
     */
    private convert(funcionario: Funcionario): Funcionario {
        const copy: Funcionario = Object.assign({}, funcionario);
        return copy;
    }
}
