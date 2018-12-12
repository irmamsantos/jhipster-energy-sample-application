import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';

type EntityResponseType = HttpResponse<IEnergyNaturalGasData>;
type EntityArrayResponseType = HttpResponse<IEnergyNaturalGasData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyNaturalGasDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-natural-gas-data';

    constructor(private http: HttpClient) {}

    create(energyNaturalGasData: IEnergyNaturalGasData): Observable<EntityResponseType> {
        return this.http.post<IEnergyNaturalGasData>(this.resourceUrl, energyNaturalGasData, { observe: 'response' });
    }

    update(energyNaturalGasData: IEnergyNaturalGasData): Observable<EntityResponseType> {
        return this.http.put<IEnergyNaturalGasData>(this.resourceUrl, energyNaturalGasData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyNaturalGasData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyNaturalGasData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
