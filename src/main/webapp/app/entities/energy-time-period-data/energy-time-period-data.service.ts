import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';

type EntityResponseType = HttpResponse<IEnergyTimePeriodData>;
type EntityArrayResponseType = HttpResponse<IEnergyTimePeriodData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyTimePeriodDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-time-period-data';

    constructor(private http: HttpClient) {}

    create(energyTimePeriodData: IEnergyTimePeriodData): Observable<EntityResponseType> {
        return this.http.post<IEnergyTimePeriodData>(this.resourceUrl, energyTimePeriodData, { observe: 'response' });
    }

    update(energyTimePeriodData: IEnergyTimePeriodData): Observable<EntityResponseType> {
        return this.http.put<IEnergyTimePeriodData>(this.resourceUrl, energyTimePeriodData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyTimePeriodData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyTimePeriodData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
