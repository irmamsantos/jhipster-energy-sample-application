import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyCycleData } from 'app/shared/model/energy-cycle-data.model';

type EntityResponseType = HttpResponse<IEnergyCycleData>;
type EntityArrayResponseType = HttpResponse<IEnergyCycleData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyCycleDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-cycle-data';

    constructor(private http: HttpClient) {}

    create(energyCycleData: IEnergyCycleData): Observable<EntityResponseType> {
        return this.http.post<IEnergyCycleData>(this.resourceUrl, energyCycleData, { observe: 'response' });
    }

    update(energyCycleData: IEnergyCycleData): Observable<EntityResponseType> {
        return this.http.put<IEnergyCycleData>(this.resourceUrl, energyCycleData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyCycleData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyCycleData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
