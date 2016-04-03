-- Copyright 2016 Marcel Haupt
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http ://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.

-- Github Project: https://github.com/cbacon93/Java-Aircraft-Instruments



if CbaconAv==nil then	-- Protection against multiple references (typically wrong script installation)


local UDPip = "192.168.178.29"
local UDPport = "9876"



package.path  = package.path..";.\\LuaSocket\\?.lua" .. ";.\\Scripts\\?.lua"
package.cpath = package.cpath..";.\\LuaSocket\\?.dll"
local socket = require("socket")


CbaconAv={

	SendTelemetry=function()
		udp = socket.udp()
		udp:settimeout(0)
		udp:setpeername(UDPip, UDPport)
		
		local altBar = LoGetAltitudeAboveSeaLevel()/0.3048;
		local pitch, bank, yaw = LoGetADIPitchBankYaw();
		
		sendstr = altBar .. ',1013,' ..  pitch .. ',' .. -bank;
		
		--ret = "";
		--for k,v in pairs(nav) do
		--	ret = ret .. ', ' .. k
		--end
		--sendstr = sendstr .. ret
		
		udp:send(sendstr);
		
		
		
	end,

}




-- (Hook) Works just after every simulation frame.
do
	local PrevLuaExportAfterNextFrame=LuaExportAfterNextFrame;

	LuaExportAfterNextFrame=function()

		CbaconAv:SendTelemetry();

		if PrevLuaExportAfterNextFrame then
			PrevLuaExportAfterNextFrame();
		end
	end
end


end						-- Protection against multiple references (typically wrong script installation)