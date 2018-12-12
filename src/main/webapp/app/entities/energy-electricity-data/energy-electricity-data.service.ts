import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyElectricityData } from 'app/shared/model/energy-electricity-data.model';

type EntityResponseType = HttpResponse<IEnergyElectricityData>;
type EntityArrayResponseType = HttpResponse<IEnergyElectricityData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyElectricityDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-electricity-data';

    constructor(private http: HttpClient) {}

    create(energyElectricityData: IEnergyElectricityData): Observable<EntityResponseType> {
        return this.http.post<IEnergyElectricityData>(this.resourceUrl, energyElectricityData, { observe: 'response' });
    }

    update(energyElectricityData: IEnergyElectricityData): Observable<EntityResponseType> {
        return this.http.put<IEnergyElectricityData>(this.resourceUrl, energyElectricityData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyElectricityData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyElectricityData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
