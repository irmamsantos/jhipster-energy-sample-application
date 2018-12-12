import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyTariffData } from 'app/shared/model/energy-tariff-data.model';

type EntityResponseType = HttpResponse<IEnergyTariffData>;
type EntityArrayResponseType = HttpResponse<IEnergyTariffData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyTariffDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-tariff-data';

    constructor(private http: HttpClient) {}

    create(energyTariffData: IEnergyTariffData): Observable<EntityResponseType> {
        return this.http.post<IEnergyTariffData>(this.resourceUrl, energyTariffData, { observe: 'response' });
    }

    update(energyTariffData: IEnergyTariffData): Observable<EntityResponseType> {
        return this.http.put<IEnergyTariffData>(this.resourceUrl, energyTariffData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyTariffData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyTariffData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
