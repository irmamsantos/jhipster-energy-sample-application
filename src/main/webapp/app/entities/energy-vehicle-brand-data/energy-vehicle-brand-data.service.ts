import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyVehicleBrandData } from 'app/shared/model/energy-vehicle-brand-data.model';

type EntityResponseType = HttpResponse<IEnergyVehicleBrandData>;
type EntityArrayResponseType = HttpResponse<IEnergyVehicleBrandData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyVehicleBrandDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-vehicle-brand-data';

    constructor(private http: HttpClient) {}

    create(energyVehicleBrandData: IEnergyVehicleBrandData): Observable<EntityResponseType> {
        return this.http.post<IEnergyVehicleBrandData>(this.resourceUrl, energyVehicleBrandData, { observe: 'response' });
    }

    update(energyVehicleBrandData: IEnergyVehicleBrandData): Observable<EntityResponseType> {
        return this.http.put<IEnergyVehicleBrandData>(this.resourceUrl, energyVehicleBrandData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyVehicleBrandData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyVehicleBrandData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
