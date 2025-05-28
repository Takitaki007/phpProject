// app/api/auth/login/route.js

import { NextResponse } from "next/server";

export async function POST(request) {
  try {
    // Extract email and password from the request body
    const { email, password } = await request.json();

    // Make the request to the external login API
    const response = await fetch("http://96.9.81.187:8080/api/v1/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email, password }),
    });

    // Check if the response is successful
    if (!response.ok) {
      const errorData = await response.json();
      return NextResponse.json(
        { error: errorData.message || "Login failed" },
        { status: 400 }
      );
    }

    // Parse the response and return it to the client
    const data = await response.json();
    return NextResponse.json(data);
  } catch (error) {
    // Handle server-side errors
    return NextResponse.json(
      { error: "Internal server error" },
      { status: 500 }
    );
  }
}
