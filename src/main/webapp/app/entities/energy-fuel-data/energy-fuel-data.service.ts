import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyFuelData } from 'app/shared/model/energy-fuel-data.model';

type EntityResponseType = HttpResponse<IEnergyFuelData>;
type EntityArrayResponseType = HttpResponse<IEnergyFuelData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyFuelDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-fuel-data';

    constructor(private http: HttpClient) {}

    create(energyFuelData: IEnergyFuelData): Observable<EntityResponseType> {
        return this.http.post<IEnergyFuelData>(this.resourceUrl, energyFuelData, { observe: 'response' });
    }

    update(energyFuelData: IEnergyFuelData): Observable<EntityResponseType> {
        return this.http.put<IEnergyFuelData>(this.resourceUrl, energyFuelData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyFuelData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyFuelData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
